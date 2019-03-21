package com.wq.configureconfigclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ConfigureConfigClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigureConfigClientApplication.class, args);
        System.out.println("【【【【【【 ConfigureConfigClientApplication微服务 】】】】】】已启动.");

    }

}
