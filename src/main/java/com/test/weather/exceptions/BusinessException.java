package com.test.weather.exceptions;

import org.springframework.http.HttpStatus;

public class BusinessException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HttpStatus httpStatus;
	
	public BusinessException(HttpStatus httpStatus) {
		super();
		this.httpStatus = httpStatus;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

}
