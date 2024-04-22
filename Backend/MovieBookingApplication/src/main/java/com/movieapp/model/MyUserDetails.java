package com.movieapp.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails {
	
	private String username;
	private String password;
	private Collection<Authorities> authorities;
	

	public MyUserDetails() {
		super();
	}

	public MyUserDetails(String username, String password, Collection<Authorities> authorities) {
		super();
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAuthorities(Collection<Authorities> authorities) {
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends Authorities> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String toString() {
		return "MyUserDetails [username=" + username + ", password=" + password + ", authorities=" + authorities + "]";
	}
	
	

}
