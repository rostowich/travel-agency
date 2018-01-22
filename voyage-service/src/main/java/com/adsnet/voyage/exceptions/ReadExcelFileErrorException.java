package com.adsnet.voyage.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This Exception is thrown when the user is trying to register with an already used email address
 * @author
 *
 */
@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class ReadExcelFileErrorException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public ReadExcelFileErrorException() {
		super();
	}

	public ReadExcelFileErrorException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
}
