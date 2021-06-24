package com.example.nacoshttpconsumer.controller;

import com.example.nacoshttpconsumer.form.IdInfo;
import com.example.nacoshttpconsumer.remoteapi.ParamQueryApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
public class NacosTestController {
    private static final Logger log = LoggerFactory.getLogger(NacosTestController.class);
    private String nacosDubboClient="http://nacos-dubbo-client";
    private String nacosHttpProducer="http://nacos-http-producer";
    @Autowired
    private  RestTemplate restTemplate;
    @Autowired
    public ApplicationContext applicationContext;
    @Autowired
    private ParamQueryApi paramQueryApi;
    @RequestMapping("/consumerconfig")
    public String oneNacosConfig(String key){
        try {
            log.info("-----------------start----------------------");
            String result = restTemplate.getForObject(nacosHttpProducer+"/producer/nacosconfig?key="+key, String.class);
            log.info("---------end-----------------");
            return "Return : " + result;
        } catch (RestClientException e) {
            log.error(e.getMessage(),e);
        }
        log.info("---------end-----------------");
        return "failed";
    }
    @RequestMapping("student")
    public Map<String,Object> queryByIdno(IdInfo idNo) {

        Map<String,Object> result = restTemplate.postForObject(nacosDubboClient+"/student",idNo, Map.class);
        return result;
    }

    @RequestMapping("/testfeign")
    public Map<String,String> testfeign(String key){
        try {
            log.info("-----------testfeign start---------------------------");
            return paramQueryApi.feignparam(key);
        } finally {
            log.info("-----------testfeign end---------------------------");
        }
    }

}
