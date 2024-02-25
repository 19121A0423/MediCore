package com.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.user.structure.ErrorStructure;

@RestControllerAdvice
public class HandleException extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure> userNotFoundByIdException(UserNotFoundByIdException exception){
		
		ErrorStructure structure = new ErrorStructure();
		structure.setMessage("User Not Found");
		structure.setRootCause(exception.getMessage());
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		
		return new ResponseEntity<ErrorStructure>(structure,HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler
	public ResponseEntity<ErrorStructure> dublicateEmailIdException(DublicateEmailIdException exception){
		
		ErrorStructure structure = new ErrorStructure();
		structure.setMessage("Dublicate EmailId ");
		structure.setRootCause(exception.getMessage());
		structure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
		
		return new ResponseEntity<ErrorStructure>(structure,HttpStatus.NOT_ACCEPTABLE);
		
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure> dublicateMobileNumberException(DublicateMobileNumberException exception){
		
		ErrorStructure structure = new ErrorStructure();
		structure.setMessage("Dublicate MobileNumber ");
		structure.setRootCause(exception.getMessage());
		structure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
		
		return new ResponseEntity<ErrorStructure>(structure,HttpStatus.NOT_ACCEPTABLE);
		
	}
	
	

}
