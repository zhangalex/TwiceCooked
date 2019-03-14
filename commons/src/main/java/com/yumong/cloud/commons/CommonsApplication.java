package com.yumong.cloud.commons;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class CommonsApplication {

    public static void main(String args[]) {
        SpringApplication.run(CommonsApplication.class, args);
    }
}
