package com.adsnet.subscription.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Montant insuffisant dans le compte")
public class InsuffisantAmountIntoAccountException extends Exception {

private static final long serialVersionUID = 1L;
	
	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public InsuffisantAmountIntoAccountException() {
		super();
	}

	public InsuffisantAmountIntoAccountException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
}
