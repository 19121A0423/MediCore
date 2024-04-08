package com.user.exception;

public class PasswordMismatchException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;

	public PasswordMismatchException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
		
}
