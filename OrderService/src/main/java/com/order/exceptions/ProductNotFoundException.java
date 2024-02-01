package com.order.exceptions;

public class ProductNotFoundException extends Exception {
	
	private static final long serialVersionUID = -7944415611428690015L;

	public ProductNotFoundException() {
	}
	
	public ProductNotFoundException(String msg) {
		super(msg);
	}

}
