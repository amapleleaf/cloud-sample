package com.example.nacoshttpproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NacosHttpProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosHttpProducerApplication.class, args);
    }

}
