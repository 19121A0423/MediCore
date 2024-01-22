package com.order.exceptions;

public class PaymentNotFoundException extends Exception {
	
	public PaymentNotFoundException() {
	}
	
	public PaymentNotFoundException(String msg) {
		super(msg);
	}

}
