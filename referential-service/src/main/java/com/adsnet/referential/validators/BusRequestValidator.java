package com.adsnet.referential.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.adsnet.referential.entities.Bus;
import com.adsnet.referential.utils.Utils;

public class BusRequestValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Bus.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Bus busValidator=(Bus) target;
		if(busValidator.getImmatriculation()==null || busValidator.getImmatriculation()=="")
			errors.rejectValue("immatriculation", "","immatriculation.required");
		if(busValidator.getType()==null)
			errors.rejectValue("type", "","type.required");
		if(busValidator.getMark()==null || busValidator.getMark()=="")
			errors.rejectValue("mark", "","mark.required");
		if(busValidator.getNumberOfPlace()==null || busValidator.getNumberOfPlace()=="")
			errors.rejectValue("numberOfPlace", "","numberOfPlace.required");
		if(!Utils.isNumeric(busValidator.getNumberOfPlace()))
			errors.rejectValue("numberOfPlace", "","numberOfPlace.isnumeric");
		
	}


}
