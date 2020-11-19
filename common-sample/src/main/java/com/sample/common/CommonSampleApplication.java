package com.sample.common;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.sample.common.dao")
public class CommonSampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommonSampleApplication.class, args);
    }
}
