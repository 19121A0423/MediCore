package com.ooms.exception;

public class EmailIdNotFoundException extends RuntimeException {

	private String message;

	public EmailIdNotFoundException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	
}
