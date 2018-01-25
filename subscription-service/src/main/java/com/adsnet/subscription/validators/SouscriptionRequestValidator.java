package com.adsnet.subscription.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.adsnet.subscription.entities.Souscription;


public class SouscriptionRequestValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Souscription.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Souscription souscriptionValidator=(Souscription) target;
		if(souscriptionValidator.getLogin()==null)
			errors.rejectValue("login", "","login.required");
		if(souscriptionValidator.getAddress()==null)
			errors.rejectValue("address", "","address.required");
		if(souscriptionValidator.getCardNumberIdentifier()==null)
			errors.rejectValue("cardNumberIdentifier", "","cardNumberIdentifier.required");
		if(souscriptionValidator.getCompleteName()==null)
			errors.rejectValue("completeName", "","completeName.required");
		if(souscriptionValidator.getEmail()==null)
			errors.rejectValue("email", "","email.required");
		if(souscriptionValidator.getAccount()==null){
			errors.rejectValue("account", "","account.required");			
		}	
	}

}
