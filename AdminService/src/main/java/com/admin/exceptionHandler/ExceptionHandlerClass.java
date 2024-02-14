package com.admin.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.admin.exception.CartIdNotFoundException;
import com.admin.exception.CategoryNotFoundException;
import com.admin.exception.ProductNotFoundException;

@RestControllerAdvice
public class ExceptionHandlerClass {

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<String> productNotFoundException(ProductNotFoundException exception){
	ResponseEntity<String> entity=new  ResponseEntity<String>(exception.getMessage(),HttpStatus.NOT_FOUND);
	return entity;
	}
	
	
	@ExceptionHandler(CategoryNotFoundException.class)
	public ResponseEntity<String> categoryNotFoundException(CategoryNotFoundException exception){
		
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(CartIdNotFoundException.class)
	public ResponseEntity<String> cartIdNotFoundException(CartIdNotFoundException exception){		
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.NOT_FOUND);
		
	}
	
	
}
