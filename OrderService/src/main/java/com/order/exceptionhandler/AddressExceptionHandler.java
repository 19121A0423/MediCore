package com.order.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.order.exceptions.AddressNotFoundException;

@ControllerAdvice
public class AddressExceptionHandler {
	
	@ExceptionHandler(value=AddressNotFoundException.class)
	public ResponseEntity<String> exceptionHandler(AddressNotFoundException exception){
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.NOT_FOUND);
	}

}
