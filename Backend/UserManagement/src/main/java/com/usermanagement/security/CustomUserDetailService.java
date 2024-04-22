package com.usermanagement.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.usermanagement.model.User;
import com.usermanagement.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	private UserRepository userRepo;
	
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtTokenHelper jwtTokenHelper;
	
	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
			User user = userRepo.findByUserName(username).get();
			System.out.println(user);
	        if (user != null) {
	            return user;
	        } else {
	            throw new UsernameNotFoundException("User not found with username: " + username);
	        }
	}
	
    private Set getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return authorities;
    }
    
    
    public UserDetails validateToken(String token) {
    	String userName = jwtTokenHelper.getUsernameFromToken(token);
    	UserDetails userDetails = loadUserByUsername(userName);
    	if(jwtTokenHelper.validateToken(token, userDetails)) {
    		return userDetails;
    	}else {
    		return null;
    	}
    }
}
