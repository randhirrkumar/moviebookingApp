  package com.usermanagement.dto;

import java.util.HashSet;
import java.util.Set;


import com.usermanagement.model.Role;

public class UserDto {
	
	private int id;
	private String fullname;
	private String username;
	private String email;
	private long phoneno;
	private Set<Role> roles = new HashSet<>();
	
	
	public UserDto() {
	}

	public UserDto(int id, String fullname, String username, String email, long phoneno, Set<Role> roles) {
		this.id = id;
		this.fullname = fullname;
		this.username = username;
		this.email = email;
		this.phoneno = phoneno;
		this.roles = roles;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(long phoneno) {
		this.phoneno = phoneno;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
