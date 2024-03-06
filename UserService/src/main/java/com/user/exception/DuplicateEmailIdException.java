package com.user.exception;

public class DuplicateEmailIdException extends Exception {
	
	private String message;

	public DuplicateEmailIdException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
		
}
