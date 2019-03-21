package com.wq.configureconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigureConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigureConfigApplication.class, args);
    }

}
