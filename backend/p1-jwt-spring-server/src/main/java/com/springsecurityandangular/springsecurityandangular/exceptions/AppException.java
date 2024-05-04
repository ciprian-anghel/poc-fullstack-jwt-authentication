package com.springsecurityandangular.springsecurityandangular.exceptions;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class AppException extends RuntimeException {

	private final HttpStatus httpStatus;
	
	public AppException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}
	
	public HttpStatus getHttpStatus() {
		return this.httpStatus;
	}
	
}
