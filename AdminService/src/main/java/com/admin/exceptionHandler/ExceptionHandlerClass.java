package com.admin.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.admin.exception.CartIdNotFoundException;
import com.admin.exception.CartListNotFoundException;
import com.admin.exception.CategoryNotFoundException;
import com.admin.exception.ProductNotFoundException;
import com.admin.exception.UserIdNotFoundException;
import com.admin.structure.ErrorStructure;

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
	
	
	
	@ExceptionHandler(UserIdNotFoundException.class)
	public ResponseEntity<ErrorStructure> userIdNotFoundException(UserIdNotFoundException exception){
		
		ErrorStructure structure = new ErrorStructure();
		structure.setMessage(exception.getMessage());
		structure.setRootCause("Data Not Found");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		
		return new ResponseEntity<ErrorStructure>(structure, HttpStatus.NOT_FOUND);
				
	}
	
	@ExceptionHandler(CartIdNotFoundException.class)
	public ResponseEntity<ErrorStructure>  cartIdNotFoundException(CartIdNotFoundException exception){	
		
		ErrorStructure structure = new ErrorStructure();
		structure.setMessage(exception.getMessage());
		structure.setRootCause("Data Not Found");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		
		return new ResponseEntity<ErrorStructure>(structure, HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(CartListNotFoundException.class)
	public ResponseEntity<ErrorStructure>  cartListNotFoundException(CartListNotFoundException exception){	
		
		ErrorStructure structure = new ErrorStructure();
		structure.setMessage(exception.getMessage());
		structure.setRootCause("List Is Empty");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());		
		return new ResponseEntity<ErrorStructure>(structure, HttpStatus.NOT_FOUND);
		
	}
	
}
