package com.user.bean;

public class PasswordUpdateRequest {

	private String email;
	private String newPassword;

	public PasswordUpdateRequest(String email, String newPassword) {
		super();
		this.email = email;
		this.newPassword = newPassword;
	}

	public PasswordUpdateRequest() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	@Override
	public String toString() {
		return "PasswordUpdateRequest [email=" + email + ", newPassword=" + newPassword + "]";
	}

}
