package com.cloud.apiconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@EnableConfigServer
public class ApiConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiConfigServerApplication.class, args);
    }

}
