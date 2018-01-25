package com.adsnet.voyage.services;

import org.springframework.stereotype.Service;

@Service
public class UrlService implements IUrlService{
	
	public static String departureHourUrl="http://referential-service/departure_hour";
			
	public static String busUrl="http://referential-service/bus";
	
	public static String pathUrl="http://referential-service/path";

	@Override
	public String getDepartureHourServiceUrl(String departureHourId) {
		// TODO Auto-generated method stub
		 return departureHourUrl+"?id="+departureHourId+"&label=&page=0";
	}

	@Override
	public String getBusServiceUrl(String busId) {
		// TODO Auto-generated method stub
		return busUrl+"?id="+busId+"&immatriculation=&numberOfPlaces=&busType=&mark=&page=0";
	}

	@Override
	public String getPathServiceUrl(String pathId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
