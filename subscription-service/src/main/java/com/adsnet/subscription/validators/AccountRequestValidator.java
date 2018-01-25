package com.adsnet.subscription.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.adsnet.subscription.entities.Account;
import com.adsnet.subscription.enums.AccountType;
import com.adsnet.subscription.utils.Utils;


public class AccountRequestValidator implements Validator {

	@Override
	public boolean supports(Class<?> account) {
		return Account.class.equals(account);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Account accountValidator=(Account) target;
		if(accountValidator.getLabel()==null)
			errors.rejectValue("label", "","label.required");
		if(accountValidator.getAccountType()==null)
			errors.rejectValue("accountType", "","accountType.required");
		if(accountValidator.getNumber()==null)
			errors.rejectValue("number", "","number.required");
		if(accountValidator.getAccountType()==AccountType.CREDIT){
			if(accountValidator.getAccountLimitType()==null)
				errors.rejectValue("accountLimitType", "","accountLimitType.required");
			if(accountValidator.getAmountLimit()==null)
				errors.rejectValue("amountLimit", "","amountLimitType.required");
			if(accountValidator.getAmountLimit()!=null && !Utils.isNumeric(accountValidator.getAmountLimit()))
				errors.rejectValue("amountLimit", "","amountLimitType.required");
		}		
	}
	
	

	
}
