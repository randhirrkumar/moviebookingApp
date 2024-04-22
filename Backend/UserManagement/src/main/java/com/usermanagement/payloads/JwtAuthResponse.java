package com.usermanagement.payloads;

import com.usermanagement.dto.UserDto;

public class JwtAuthResponse {

	private UserDto user;
	private String jwtToken;
	
	public JwtAuthResponse() {
		super();
	}
	
	public JwtAuthResponse(UserDto user, String jwtToken) {
		super();
		this.user = user;
		this.jwtToken = jwtToken;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	public String getJwtToken() {
		return jwtToken;
	}
	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
	
}
