package com.example.nacosdubbo.service;

import com.sample.cloud.serviceapi.api.ISmsService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class SmsService implements ISmsService {
    public void sendSms(String msg){
        System.out.print("send->"+msg);
    }
}
