package com.order.exceptions;

public class CartNotFoundException extends Exception {
	
	private static final long serialVersionUID = -7944415611428690015L;

	public CartNotFoundException() {
	}
	
	public CartNotFoundException(String msg) {
		super(msg);
	}

}
