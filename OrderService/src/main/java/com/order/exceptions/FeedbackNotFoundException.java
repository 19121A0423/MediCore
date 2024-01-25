package com.order.exceptions;

public class FeedbackNotFoundException extends Exception {

	private static final long serialVersionUID = 4413171927934967094L;

	public FeedbackNotFoundException() {
	}
	
	public FeedbackNotFoundException(String msg) {
		super(msg);
	}

}
