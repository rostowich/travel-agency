package com.adsnet.voyage.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.adsnet.voyage.entities.Voyage;
import com.adsnet.voyage.utils.Utils;


public class VoyageRequestValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Voyage.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Voyage voyageValidator=(Voyage) target;
		if(voyageValidator.getDepartureDate()==null)
			errors.rejectValue("departureDate", "","departureDate.required");
		if(voyageValidator.getDepartureHourId()==null)
			errors.rejectValue("departureHour", "","departureHourId.required");
		if(voyageValidator.getPathId()==null)
			errors.rejectValue("path", "","pathId.required");
		if(!Utils.isNumeric(voyageValidator.getNumberOfPlace()+""))
			errors.rejectValue("numberOfPlace", "","numberOfPlace.numeric");
	}

}
