package com.examples.productInfo.exceptions;

import org.springframework.http.HttpStatus;

public class ProductAlreadyExistsException extends RuntimeException {
	
	private String message;
	private HttpStatus status;

	public ProductAlreadyExistsException() {
		super();
		this.message = "Product Already Exists";
		this.status = HttpStatus.CONFLICT;
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
