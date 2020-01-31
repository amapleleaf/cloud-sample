package com.sample.cloud.serviceapi.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("serviceapi")
public interface HelloRemoteApi {
    @RequestMapping(name = "/hello")
    public Student hello(@RequestParam("name") String name);
}
