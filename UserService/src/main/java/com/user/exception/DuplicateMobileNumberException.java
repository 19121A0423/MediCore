package com.user.exception;


public class DuplicateMobileNumberException extends Exception{

	private String message;

	public DuplicateMobileNumberException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
