package com.example.courierapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class CourierApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourierApiApplication.class, args);
    }

}
