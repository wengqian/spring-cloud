package com.wq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ConfigureConfigClient01Application {

	public static void main(String[] args) {
		SpringApplication.run(ConfigureConfigClient01Application.class, args);
		System.out.println("【【【【【【 ConfigureConfigClient01Application微服务 】】】】】】已启动.");
	}
}
