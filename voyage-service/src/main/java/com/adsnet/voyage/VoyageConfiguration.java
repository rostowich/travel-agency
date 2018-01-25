package com.adsnet.voyage;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class VoyageConfiguration {
	
	public static String REFERENTIAL_SERVICE_URL="http://referential-service";

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	@Bean
	public  String ReferentialServiceUrl(){
		return REFERENTIAL_SERVICE_URL;
	}
}
