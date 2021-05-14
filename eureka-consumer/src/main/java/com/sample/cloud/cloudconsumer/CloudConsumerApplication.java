package com.sample.cloud.cloudconsumer;

import com.sample.cloud.apiclient.ApiClientScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
//@EnableCircuitBreaker
@ApiClientScan("com.sample.cloud.cloudconsumer.apiclient")
public class CloudConsumerApplication {
	public static void main(String[] args) {
		SpringApplication.run(CloudConsumerApplication.class, args);
	}
}
