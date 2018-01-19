package com.adsnet.referential.utils;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public interface UtilityFactory {

	Date getDate();
	
	Integer generatePin();
}
