package com.ecram.usersmicroecram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class UsersMicroEcramApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersMicroEcramApplication.class, args);
	}

}
