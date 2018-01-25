package com.adsnet.subscription.utils;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public interface UtilityFactory {

	Date getDate();
	
	Integer generatePin();
}
