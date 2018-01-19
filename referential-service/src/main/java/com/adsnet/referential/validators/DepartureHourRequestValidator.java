package com.adsnet.referential.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.adsnet.referential.entities.DepartureHour;

/**
 * This class is used to validate request concerning the city entity
 * @author Rostow
 *
 */
public class DepartureHourRequestValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return DepartureHour.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		DepartureHour departureHourValidator=(DepartureHour) target;
		if(departureHourValidator.getLabel()==null || departureHourValidator.getLabel()=="")
			errors.rejectValue("label", "","label.required");
	}
	
}
