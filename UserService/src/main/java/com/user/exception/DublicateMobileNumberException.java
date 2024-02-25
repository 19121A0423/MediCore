package com.user.exception;


public class DublicateMobileNumberException extends Exception{

	private String message;

	public DublicateMobileNumberException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
