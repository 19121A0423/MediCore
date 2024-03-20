package com.order.exceptions;

public class UserNotFoundException extends Exception {
	
	private static final long serialVersionUID = -7944415611428690015L;

	public UserNotFoundException() {
	}
	
	public UserNotFoundException(String msg) {
		super(msg);
	}

}
