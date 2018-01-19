package org.opendevup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class ATravelConfigServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ATravelConfigServiceApplication.class, args);
	}
}
