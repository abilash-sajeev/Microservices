package com.examples.store.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class ApplicationExceptionHandler {

	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<String> handleHttpClientErrorException(HttpClientErrorException ex){
		System.out.println(ex.getStatusCode());
		System.out.println(ex.getMessage());
		System.out.println(new ResponseEntity<>(ex.getMessage(), ex.getStatusCode()));
		if (ex.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
			return new ResponseEntity(new ProductNotFoundException().toString(), HttpStatus.NOT_FOUND);
		}else if (ex.getStatusCode().equals(HttpStatus.FORBIDDEN)){
			return new ResponseEntity(new AccessDeniedException().toString(), HttpStatus.FORBIDDEN);
		}else {
			return new ResponseEntity<>(ex.getMessage(), ex.getStatusCode());
		}
	}
}
