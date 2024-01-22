package com.order.exceptions;

public class OrderNotFoundException extends Exception {
	
	public OrderNotFoundException() {
	}
	
	public OrderNotFoundException(String msg) {
		super(msg);
	}

}
