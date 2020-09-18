package com.example.nacoshttpproducer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class NacosTestController {
    @Autowired
    private Environment env;
    @Value("${email.address:}")
    private String emailAddress;
    @RequestMapping("nacosconfig")
    public Map<String,String> oneNacosConfig(String key){
        Map<String,String> result = new HashMap<>();
        result.put("email.address",emailAddress);
        result.put(key,env.getProperty(key));
        result.put("server.port",env.getProperty("server.port"));
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
}
