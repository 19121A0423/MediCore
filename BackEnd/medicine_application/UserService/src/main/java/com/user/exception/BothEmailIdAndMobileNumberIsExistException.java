package com.user.exception;

public class BothEmailIdAndMobileNumberIsExistException extends Exception {
	private String message;

	public BothEmailIdAndMobileNumberIsExistException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	
	
	
}
