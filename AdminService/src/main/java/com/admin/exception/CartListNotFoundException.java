package com.admin.exception;

public class CartListNotFoundException extends Exception {
	
	private String message;
	
	
	public CartListNotFoundException(String message) {
		super();
		this.message = message;
	}


	public String getMessage() {
		return message;
	}
	
	

}
