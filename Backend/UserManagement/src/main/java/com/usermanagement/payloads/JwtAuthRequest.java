package com.usermanagement.payloads;

public class JwtAuthRequest {

	private String username;
	
	private String password;
	
	public JwtAuthRequest() {
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	
}
