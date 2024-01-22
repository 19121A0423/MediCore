package com.order.exceptions;

public class CartNotFoundException extends Exception {
	
	public CartNotFoundException() {
	}
	
	public CartNotFoundException(String msg) {
		super(msg);
	}

}
