package com.user.exception;

public class InvalidOTPException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;

	public InvalidOTPException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
		
}
