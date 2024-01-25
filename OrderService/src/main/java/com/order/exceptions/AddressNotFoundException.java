package com.order.exceptions;

public class AddressNotFoundException extends Exception {
	
	private static final long serialVersionUID = -7944415611428690015L;

	public AddressNotFoundException() {
	}
	
	public AddressNotFoundException(String msg) {
		super(msg);
	}

}
