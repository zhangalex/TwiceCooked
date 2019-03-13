package com.yumong.cloud.authorize;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class AuthorizeApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorizeApplication.class, args);
    }
}
