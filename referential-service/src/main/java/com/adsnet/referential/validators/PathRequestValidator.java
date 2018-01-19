package com.adsnet.referential.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.adsnet.referential.entities.Path;

/**
 * This class is used to validate request concerning the path entity
 * @author Rostow
 *
 */
public class PathRequestValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Path.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Path pathValidator=(Path) target;
		if(pathValidator.getDeparture().getId()==null)
			errors.rejectValue("departure", "","departure.required");
		else if(pathValidator.getDestination().getId()==null)
			errors.rejectValue("destination", "","destination.required");
		else if(pathValidator.getDeparture().getId()==pathValidator.getDestination().getId())
			errors.rejectValue("departure", "","departure.destination");
	}
	
}
