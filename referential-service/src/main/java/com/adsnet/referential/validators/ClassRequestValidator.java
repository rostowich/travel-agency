package com.adsnet.referential.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.adsnet.referential.entities.Classe;

/**
 * This class is used to validate request concerning the path entity
 * @author Rostow
 *
 */
public class ClassRequestValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Classe.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Classe pathValidator=(Classe) target;
		if(pathValidator.getLabel()==null)
			errors.rejectValue("label", "","label.required");
		if(pathValidator.getPath().getId()==null)
			errors.rejectValue("path", "","path.required");
		
	}
	
}
