package com.usermanagement.model;

public class ForgotPassword {
	
	private String username;
    private String email;
    private String question;
    private String newPassword;
    
    
	public ForgotPassword() {
		super();
	}
	
	public ForgotPassword(String username, String email, String question, String newPassword) {
		super();
		this.username = username;
		this.email = email;
		this.question = question;
		this.newPassword = newPassword;
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
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
    
}
