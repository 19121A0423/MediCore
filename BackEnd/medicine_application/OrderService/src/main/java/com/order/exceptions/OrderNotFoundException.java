package com.order.exceptions;

public class OrderNotFoundException extends Exception {

	private static final long serialVersionUID = 7802186579251474574L;

	public OrderNotFoundException() {
	}
	
	public OrderNotFoundException(String msg) {
		super(msg);
	}

}
