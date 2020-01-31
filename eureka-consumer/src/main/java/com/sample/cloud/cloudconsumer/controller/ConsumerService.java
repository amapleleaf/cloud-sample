package com.sample.cloud.cloudconsumer.controller;

import com.sample.cloud.cloudconsumer.api.HelloRemoteService;
import org.springframework.beans.factory.annotation.Autowired;

public class ConsumerService {
    @Autowired
    private HelloRemoteService helloRemoteService;

}
