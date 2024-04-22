package com.usermanagement.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.usermanagement.config.AppConstants;
import com.usermanagement.model.Role;
import com.usermanagement.model.User;
import com.usermanagement.repository.RoleRepository;
import com.usermanagement.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;
	
	
	@Override
	public User registerUser(User user) throws Exception {
		Optional<User> local = this.userRepo.findByUserName(user.getUsername());
		 if (local.isPresent()) {
			System.out.println("User is already there !!");
			throw new Exception("User is already present");
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		Role role = roleRepository.findById(AppConstants.NORMAL_USER).get();
		user.getRoles().add(role);
		User newUser = userRepo.saveAndFlush(user);
		return newUser;
	}

	
	@Override
	public User forgotPassword(User user, String email, String question, String newPassword) throws Exception {
	    Optional<User> local = this.userRepo.findByUserName(user.getUsername());
	    if (local.isPresent()) {
	        User foundUser = local.get();
	        if (foundUser.getEmail().equals(email) && foundUser.getQuestion().equals(question)) {
	            foundUser.setPassword(newPassword);
	            userRepo.save(foundUser);
	            return foundUser;
	        } else {
	            throw new IllegalArgumentException("Invalid username, email, or security answer");
	        }
	    } else {
	        throw new IllegalArgumentException("User not found");
	    }
	}

}