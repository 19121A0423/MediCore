package com.order.exceptions;

public class PaymentNotFoundException extends Exception {

	private static final long serialVersionUID = -6181171506470124820L;

	public PaymentNotFoundException() {
	}
	
	public PaymentNotFoundException(String msg) {
		super(msg);
	}

}
