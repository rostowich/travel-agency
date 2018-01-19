package com.adsnet.referential.validators;


import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.adsnet.referential.entities.Driver;


public class DriverRequestValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Driver.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Driver driverValidator=(Driver) target;
		if(driverValidator.getDriverLicenceNumber()==null || driverValidator.getDriverLicenceNumber()=="")
			errors.rejectValue("driverLicenceNumber", "","driverLicenceNumber.required");
		if(driverValidator.getIdCardNumber()==null)
			errors.rejectValue("idCardNumber", "","idCardNumber.required");
		if(driverValidator.getFullName()==null || driverValidator.getFullName()=="")
			errors.rejectValue("fullName", "","fullName.required");
		if(driverValidator.getDriverLicenceDeliveryDate()==null )
			errors.rejectValue("driverLicenceDeliveryDate", "","driverLicenceDeliveryDate.required");
		if(driverValidator.getDriverLicenceExpirationDate()==null )
			errors.rejectValue("driverLicenceExpirationDate", "","driverLicenceExpirationDate.required");
		if(driverValidator.getDriverLicenceDeliveryDate().compareTo(driverValidator.getDriverLicenceExpirationDate())>0 )
			errors.rejectValue("driverLicenceExpirationDate", "","driverLicenceExpirationDate.superior");
	}

}
