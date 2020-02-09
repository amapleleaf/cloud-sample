package com.example.nacoshttpconsumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class NacosTestController {
    private String nacosHttpProducer="http://nacos-http-producer";
    @Autowired
    private  RestTemplate restTemplate;
    @RequestMapping("/nacosconfig")
    public String oneNacosConfig(String key){

        String result = restTemplate.getForObject(nacosHttpProducer+"/nacosconfig?key="+key, String.class);
        return "Return : " + result;
    }
}
