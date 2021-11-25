package com.examples.productInfo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {
	
	@ExceptionHandler(ProductAlreadyExistsException.class)
	public ResponseEntity<String> handleProductAlreadyExistsException(){
		return new ResponseEntity(new ProductAlreadyExistsException().toString(), HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<String> handleProductNotFoundException(){
		return new ResponseEntity(new ProductNotFoundException().toString(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<String> handleAccessDeniedException(){
		return new ResponseEntity(new AccessDeniedException().toString(), HttpStatus.FORBIDDEN);
	}

}
