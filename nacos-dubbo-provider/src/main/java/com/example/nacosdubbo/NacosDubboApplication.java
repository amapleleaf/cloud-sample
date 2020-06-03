package com.example.nacosdubbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NacosDubboApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosDubboApplication.class, args);
    }

}
