package com.adsnet.voyage.services;

import org.springframework.stereotype.Component;

@Component
public interface IUrlService {

	public String getDepartureHourServiceUrl(String departureHourId);
	
	public String getBusServiceUrl(String budId);
	
	public String getPathServiceUrl(String pathId);
}
