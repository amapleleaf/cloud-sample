package com.sample.cloud.cloudconsumer.controller;

import com.sample.cloud.cloudconsumer.api.HelloRemoteService;
import com.sample.cloud.cloudconsumer.apiclient.SmsService;
import com.sample.cloud.serviceapi.api.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class ConsumerController {
    @Autowired
    private HelloRemoteService helloRemoteService;
    @Autowired
    private SmsService smsApiClientService;
    @RequestMapping("/query")
    public Student query(@RequestParam("name") String name) {
        return helloRemoteService.hello(name);
    }
    @RequestMapping("/sendSms")
    public String sendSms(){
        return smsApiClientService.sendSms("sssss");
    }
}
