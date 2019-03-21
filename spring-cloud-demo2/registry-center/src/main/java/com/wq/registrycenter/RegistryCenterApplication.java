package com.wq.registrycenter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@EnableEurekaServer //启动一个服务注册中心提供给其他应用进行对话
public class RegistryCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegistryCenterApplication.class, args);
	}
	@Value("${server.port}")
	private String port;

	@Value("${eureka.instance.hostname}")
	private String ip;

	@RequestMapping("/test")
	public String index(){
		return ip+":"+port+"";
	}
}
