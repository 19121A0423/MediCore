package com.admin.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.admin.exception.CategoryNotFoundException;

public class CategoryExceptionHandler {

	@ExceptionHandler(CategoryNotFoundException.class)
	public ResponseEntity<String> exceptionHandling(CategoryNotFoundException exception){
		
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.NOT_FOUND);
		
	}
}
