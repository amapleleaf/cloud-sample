package com.example.springcloudstream;

import com.example.springcloudstream.amqp.SysMessageNoticeChannelIn;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
public class SpringCloudSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudSampleApplication.class, args);
    }

}
