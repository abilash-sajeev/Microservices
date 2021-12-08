package com.examples.store.exceptions;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class ApplicationExceptionHandler {
	
	Logger logger = LoggerFactory.getLogger(ApplicationExceptionHandler.class);

	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<String> handleHttpClientErrorException(HttpClientErrorException ex){
		if (ex.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
			logger.error("Product not found", new ProductNotFoundException());
			return new ResponseEntity(new ProductNotFoundException().toString(), HttpStatus.NOT_FOUND);
		}else if (ex.getStatusCode().equals(HttpStatus.FORBIDDEN)){
			return new ResponseEntity(new AccessDeniedException().toString(), HttpStatus.FORBIDDEN);
		}else {
			return new ResponseEntity<>(ex.getMessage(), ex.getStatusCode());
		}
	}
}
