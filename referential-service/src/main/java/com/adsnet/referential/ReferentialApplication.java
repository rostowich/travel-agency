package com.adsnet.referential;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient 
@SpringBootApplication
public class ReferentialApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReferentialApplication.class, args);
	}
}
