package com.example.nacoshttpconsumer.remoteapi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "nacos-http-producer")
public interface ParamQueryApi {
    @RequestMapping(value = "/producer/feignparam",method = RequestMethod.POST)
    Map<String,String> feignparam(@RequestParam("key") String  key);
}
