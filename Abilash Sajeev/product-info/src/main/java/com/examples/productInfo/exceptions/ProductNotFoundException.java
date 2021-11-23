package com.examples.productInfo.exceptions;

import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends RuntimeException{
	
	private String message;
	private HttpStatus status;

	public ProductNotFoundException() {
		super();
		this.message = "Product Not Found";
		this.status = HttpStatus.NOT_FOUND;
	}

	public String getMessage() {
		return message;
	}

	public HttpStatus getStatus() {
		return status;
	}
	
	@Override
	public String toString() {
		return "Status Code: " + status + "\nError Message: " + message;
	}
	
}
