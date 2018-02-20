package com.maviance.protecting_queen.services;

import org.springframework.stereotype.Component;

import com.maviance.protecting_queen.domain.User;
import com.maviance.protecting_queen.exceptions.UserAlreadyExistException;


@Component
public interface SignUpService {

	/**
	 * This method is used to insert a new user into the database
	 * @param user
	 * @return
	 */
	public User signUpUser(User user) throws UserAlreadyExistException;
		
}
