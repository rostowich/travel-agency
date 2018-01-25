package com.adsnet.subscription.utils;

import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class UtilityFactoryImpl implements UtilityFactory{

	private static int MIN=1000;
	
	private static int MAX=9999;
	@Override
	public Date getDate() {
		// TODO Auto-generated method stub
		return new Date();
	}

	@Override
	public Integer generatePin() {
		// TODO Auto-generated method stub
		Random r = new Random();
		return r.nextInt((MAX - MIN) + 1) + MIN;
	}

}
