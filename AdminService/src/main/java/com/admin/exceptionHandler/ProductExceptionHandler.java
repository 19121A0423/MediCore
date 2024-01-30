package com.admin.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.admin.exception.ProductNotFoundException;

public class ProductExceptionHandler {

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<String> excptionHandling(ProductNotFoundException exception){
	ResponseEntity<String> entity=new  ResponseEntity<String>(exception.getMessage(),HttpStatus.NOT_FOUND);
	return entity;
	}
}
