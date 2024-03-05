package com.admin.exception;

public class CompositionNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CompositionNotFoundException() {
		
	}
	
	
	public CompositionNotFoundException(String message) {
		super(message);
	}

}
