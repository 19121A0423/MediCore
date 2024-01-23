package com.ooms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ooms.util.ErrorStructure;

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
	public ResponseEntity<ErrorStructure> emailIdNotFoundException(EmailIdNotFoundException exception){
		
		ErrorStructure structure = new ErrorStructure();
		structure.setMessage("Email Not Found");
		structure.setRootCause(exception.getMessage());
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ErrorStructure>(structure,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure> invalidPasswordExceptio(InvalidPasswordException exception){
		
		ErrorStructure structure = new ErrorStructure();
		structure.setMessage("Password Mismatch");
		structure.setRootCause(exception.getMessage());
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ErrorStructure>(structure,HttpStatus.NOT_FOUND);
		
	}

}
