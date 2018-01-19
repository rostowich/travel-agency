package com.adsnet.referential.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.adsnet.referential.entities.CashRegister;

/**
 * This class is used to validate request concerning the city entity
 * @author Rostow
 *
 */
public class CashRegisterRequestValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return CashRegister.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		CashRegister cashRegisterValidator=(CashRegister) target;
		if(cashRegisterValidator.getLabel()==null || cashRegisterValidator.getLabel()=="")
			errors.rejectValue("label", "","label.required");
	}
	
}
