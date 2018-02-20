package com.maviance.protecting_queen.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class QueenIsfallingIntoTheValleyException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public QueenIsfallingIntoTheValleyException() {
		super();
	}

	public QueenIsfallingIntoTheValleyException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
}
