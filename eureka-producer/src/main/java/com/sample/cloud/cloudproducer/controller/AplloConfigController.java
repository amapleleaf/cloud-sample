package com.sample.cloud.cloudproducer.controller;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AplloConfigController {
    @ApolloConfig
    private Config config;
    @RequestMapping("/getConfig")
    public Map<String,String> getApolloConfig(String key){
        Map<String,String> result = new HashMap<>();
        result.put(key,config.getProperty(key,config.getProperty(key, "--")));
        return result;
    }
    @RequestMapping("/queryUser")
    public Map<String,String> queryUser(String user_id){
        Map<String,String> result = new HashMap<>();
        result.put("userId", user_id);
        result.put("userName", "张三丰");
        result.put("age", "18");
        return result;
    }
}
