package com.usermanagement.service;

import com.usermanagement.model.User;

public interface UserService {
	
	public User registerUser(User user) throws Exception;
	public User forgotPassword(User user, String email, String question, String newPassword) throws Exception;
}
