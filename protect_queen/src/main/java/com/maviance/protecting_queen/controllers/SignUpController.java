package com.maviance.protecting_queen.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.maviance.protecting_queen.domain.User;
import com.maviance.protecting_queen.domain.UserRegistration;
import com.maviance.protecting_queen.exceptions.UserAlreadyExistException;
import com.maviance.protecting_queen.services.SignUpService;
import com.maviance.protecting_queen.validation.SignUpValidator;

@RestController
public class SignUpController {

	/**
	 * We inject the SignUpService to save the user into the database
	 */
	@Autowired
	private SignUpService signupService;
	
	/**
	 * We set the validator. This validator will be used  to validate  the UserRegistration object
	 * @param binder
	 */
	@InitBinder()
	protected void initBinder(WebDataBinder binder) {
	    binder.setValidator(new SignUpValidator());
	}
	
	/**
	 * This method takes a User object and insert into the database if the user does not exist
	 * @param user
	 * @return
	 * @throws UserAlreadyExistException
	 */
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ResponseEntity<User> signup(@Valid @RequestBody  UserRegistration userRegistration) throws UserAlreadyExistException{
		User user=new User(userRegistration.getUsername(), userRegistration.getPassword());
		signupService.signUpUser(user);
		return new ResponseEntity<User>(HttpStatus.CREATED);
	}
}
