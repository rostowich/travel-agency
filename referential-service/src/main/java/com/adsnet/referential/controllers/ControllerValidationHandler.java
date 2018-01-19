package com.adsnet.referential.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.adsnet.referential.objects.MessageDTO;
import com.adsnet.referential.objects.MessageType;



/**
 * This class is a controller advice that will intercept exceptions of a specific kind
 * and return something else. It return a MessageDTO object with the type and the message of the 
 * error
 * This class will be applied to each controller
 */
@ControllerAdvice
public class ControllerValidationHandler {

	@Autowired
	private MessageSource msgSource;
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public MessageDTO processValidationError(MethodArgumentNotValidException ex) {
	    BindingResult result = ex.getBindingResult();
	    FieldError error = result.getFieldError();

	    return processFieldError(error);
	  }

	 private MessageDTO processFieldError(FieldError error) {
	    MessageDTO message = null;
	    if (error != null) {
	      String msg = msgSource.getMessage(error.getDefaultMessage(), null, null);
	      message = new MessageDTO(msg, MessageType.ERROR);
	    }
	    return message;
	  }
	 
	 
}
