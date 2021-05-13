package com.example.nacoshttpconsumer.controller;

import com.example.nacoshttpconsumer.form.IdInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
public class NacosTestController {
    private String nacosDubboClient="http://nacos-dubbo-client";
    private String nacosHttpProducer="http://nacos-http-producer";
    @Autowired
    private  RestTemplate restTemplate;
    @Autowired
    public ApplicationContext applicationContext;
    @RequestMapping("/consumerconfig")
    public String oneNacosConfig(String key){

        String result = restTemplate.getForObject(nacosHttpProducer+"/producer/nacosconfig?key="+key, String.class);
        return "Return : " + result;
    }
    @RequestMapping("student")
    public Map<String,Object> queryByIdno(IdInfo idNo) {

        Map<String,Object> result = restTemplate.postForObject(nacosDubboClient+"/student",idNo, Map.class);
        return result;
    }
}
