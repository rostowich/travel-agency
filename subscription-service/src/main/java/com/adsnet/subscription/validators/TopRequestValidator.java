package com.adsnet.subscription.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.adsnet.subscription.entities.TopUp;
import com.adsnet.subscription.utils.Utils;


public class TopRequestValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return TopUp.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		TopUp topUpValidator=(TopUp) target;
		if(topUpValidator.getAmount()==null)
			errors.rejectValue("amount", "","amount.required");
		if(topUpValidator.getAccount()==null)
			errors.rejectValue("account", "","account.required");
		if(topUpValidator.getTopUpType()==null)
			errors.rejectValue("topUpType", "","topupType.required");
		if(!Utils.isNumeric(topUpValidator.getAmount()))
			errors.rejectValue("amount", "","amount.numeric");
	}

}
