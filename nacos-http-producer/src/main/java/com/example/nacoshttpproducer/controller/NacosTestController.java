package com.example.nacoshttpproducer.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RefreshScope
public class NacosTestController {
    @Autowired
    public ApplicationContext applicationContext;
    @Autowired
    private Environment env;
    @Value("${email.address:}")
    private String emailAddress;
    @RequestMapping("nacosconfig")
    public Map<String,String> oneNacosConfig(String  key){
        Map<String,String> result = new HashMap<>();
        result.put("email.address",emailAddress);
        result.put(key,env.getProperty(key));
        result.put("server.port",env.getProperty("server.port"));
        if("test".equals(key)){
            //支持从nacos配置复杂的格式
            result.put(key, JSON.toJSONString(applicationContext.getBean("testProperties")));
        }
        try {
            Thread.sleep(15*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
    @RequestMapping("userconfig/{key}")
    public Map<String,String> user(@PathVariable String key){
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
    @RequestMapping("feignparam")
    public Map<String,String> feignparam(String  key){
        Map<String,String> result = new HashMap<>();
        result.put("email.address",emailAddress);
        result.put(key,env.getProperty(key));
        result.put("server.port",env.getProperty("server.port"));
        if("test".equals(key)){
            //支持从nacos配置复杂的格式
            result.put(key, JSON.toJSONString(applicationContext.getBean("testProperties")));
        }
        try {
            Thread.sleep(15*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
}
