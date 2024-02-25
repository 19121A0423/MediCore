package com.user.exception;

public class DublicateEmailIdException extends Exception {
	
	private String message;

	public DublicateEmailIdException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	

}
