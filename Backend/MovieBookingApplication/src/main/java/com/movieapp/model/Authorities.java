package com.movieapp.model;

import org.springframework.security.core.GrantedAuthority;

public class Authorities implements GrantedAuthority {
	
	private String authority;
	
	public Authorities() {
		super();
	}

	public Authorities(String authority) {
		super();
		this.authority = authority;
	}
	
	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String getAuthority() {
		return this.authority;
	}

	@Override
	public String toString() {
		return "Authorities [authority=" + authority + "]";
	}
	
}
