package com.adsnet.referential.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.adsnet.referential.entities.City;

/**
 * This class is used to validate request concerning the city entity
 * @author Rostow
 *
 */
public class CityRequestValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return City.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		City cityValidator=(City) target;
		if(cityValidator.getLabel()==null || cityValidator.getLabel()=="")
			errors.rejectValue("label", "","label.required");
	}
	
}
