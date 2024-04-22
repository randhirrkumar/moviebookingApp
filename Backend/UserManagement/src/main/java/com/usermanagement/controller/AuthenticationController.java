package com.usermanagement.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usermanagement.dto.UserDto;
import com.usermanagement.model.ForgotPassword;
import com.usermanagement.model.User;
import com.usermanagement.payloads.JwtAuthRequest;
import com.usermanagement.payloads.JwtAuthResponse;
import com.usermanagement.security.CustomUserDetailService;
import com.usermanagement.security.JwtTokenHelper;
import com.usermanagement.service.UserService;


@RestController
@RequestMapping("auth/v1.0")
@CrossOrigin("*")
public class AuthenticationController {
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper; 
	
	@Autowired
	private CustomUserDetailService customUserDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService uService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	

	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception{
		authenticate(request.getUsername(), request.getPassword());
		
		User user = this.customUserDetailsService.loadUserByUsername(request.getUsername());
		
		String token = this.jwtTokenHelper.generateToken(user.getUsername());
		UserDto userWithoutPassword = new UserDto(user.getId(),user.getFullname(),user.getUsername(),user.getEmail(),user.getPhoneno(),user.getRoles());
		JwtAuthResponse response = new JwtAuthResponse();
		response.setJwtToken(token);
		response.setUser(userWithoutPassword);
		
		
		return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
		
	}
	
	
	private void authenticate(String username, String password) throws Exception {
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		System.out.println("Validate method " + authenticationToken);
		
		try {
			this.authenticationManager.authenticate(authenticationToken);
		}
		catch(BadCredentialsException e) {
			System.out.println("Invalid Details !!");
			throw new Exception("Invalid username or password !!");
		}
		
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user) throws Exception{
		if(user==null) {
			return new ResponseEntity<String>("User cannot be null" , HttpStatus.INTERNAL_SERVER_ERROR);
		}
		User registeredUser = uService.registerUser(user);
		return new ResponseEntity<User>(registeredUser, HttpStatus.CREATED);
	}
	
	@GetMapping("/current-user")
	public User getCurrentUser(Principal principal) {
		if(principal == null) {
			throw new IllegalArgumentException("User cannot be authenticated");
		}
		return ((User)this.customUserDetailsService.loadUserByUsername(principal.getName()));
	}
	
	@PostMapping("/validateToken")
	public ResponseEntity<UserDetails> validateToken(@RequestBody String token) {
		System.out.println("token "+token);
		UserDetails userDetails =  customUserDetailsService.validateToken(token);
		System.out.println(userDetails);
		return new ResponseEntity<UserDetails>(userDetails, HttpStatus.OK);
	}
	
	@PostMapping("/forgotPassword")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPassword request) {
        try {
            User user = new User();
            user.setUsername(request.getUsername());
            
            String newPassword = passwordEncoder.encode(request.getNewPassword());
            uService.forgotPassword(user, request.getEmail(), request.getQuestion(), newPassword);
            
            return ResponseEntity.ok("Password reset successful");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }
}
