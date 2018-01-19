package org.opendevup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class TravelRegistrationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelRegistrationServiceApplication.class, args);
	}
}
