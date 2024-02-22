package com.admin.exception;

public class CartListNotFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	
	
	public CartListNotFoundException(String message) {
		super();
		this.message = message;
	}


	public String getMessage() {
		return message;
	}
	
	

}
