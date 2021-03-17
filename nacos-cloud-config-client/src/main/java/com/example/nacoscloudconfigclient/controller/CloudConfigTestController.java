package com.example.nacoscloudconfigclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CloudConfigTestController {
    @Autowired
    public ApplicationContext applicationContext;
    @Autowired
    private Environment env;
    @Value("${email.address:}")
    private String emailAddress;
    @Value("${alarm.email:}")
    private List<String> alarmEmail;
    @RequestMapping("cloudconfig")
    public Map<String,Object> oneNacosConfig(String  key){
        Map<String,Object> result = new HashMap<>();
        result.put("email.address",emailAddress);
        result.put(key,env.getProperty(key));
        result.put("server.port",env.getProperty("server.port"));
        result.put("alarm.email",alarmEmail);
        return result;
    }
}
