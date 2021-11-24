package com.example.outlet.service;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionsHandler {
	@ExceptionHandler(Exception.class)
	  public String resourceNotFoundException(Exception ex, WebRequest request) {
	    
	    return "resourse not found";
	  }
	}
 