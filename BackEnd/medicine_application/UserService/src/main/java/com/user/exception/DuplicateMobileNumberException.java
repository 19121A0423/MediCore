package com.user.exception;


public class DuplicateMobileNumberException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;

	public DuplicateMobileNumberException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
