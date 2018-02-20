package com.maviance.protecting_queen.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="This command is invalid")
public class InvalidCommandException extends Exception {

private static final long serialVersionUID = 1L;
	
	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public InvalidCommandException() {
		super();
	}

	public InvalidCommandException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
}
