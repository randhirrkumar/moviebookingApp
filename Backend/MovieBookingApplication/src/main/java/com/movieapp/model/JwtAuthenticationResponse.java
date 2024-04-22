package com.movieapp.model;

public class JwtAuthenticationResponse {
	
	private String jwtToken;
	
	public JwtAuthenticationResponse() {
		super();
	}
	public JwtAuthenticationResponse(String jwtToken) {
		super();
		this.jwtToken = jwtToken;
	}
	public String getJwtToken() {
		return jwtToken;
	}
	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

}
