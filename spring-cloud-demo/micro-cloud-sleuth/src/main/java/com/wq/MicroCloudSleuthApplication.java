package com.wq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient // 配置本应用将使用服务注册和服务发现
@SpringBootApplication
public class MicroCloudSleuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroCloudSleuthApplication.class, args);
	}
}
