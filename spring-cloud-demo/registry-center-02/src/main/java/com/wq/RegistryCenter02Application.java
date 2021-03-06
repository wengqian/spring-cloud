package com.wq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RegistryCenter02Application {

	public static void main(String[] args) {
		SpringApplication.run(RegistryCenter02Application.class, args);
	}
}
