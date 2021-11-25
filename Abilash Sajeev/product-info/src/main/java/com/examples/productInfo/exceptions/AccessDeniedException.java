package com.examples.productInfo.exceptions;

import org.springframework.http.HttpStatus;

public class AccessDeniedException extends RuntimeException {

	private String message;
	private HttpStatus status;

	public AccessDeniedException() {
		super();
		this.message = "You don't have permission to access / on this server";
		this.status = HttpStatus.FORBIDDEN;
	}

	public String getMessage() {
		return message;
	}

	public HttpStatus getStatus() {
		return status;
	}
	
	@Override
	public String toString() {
		return status + ": " +  message;
	}
	
}
