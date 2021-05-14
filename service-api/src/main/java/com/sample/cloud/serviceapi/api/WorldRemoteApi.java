package com.sample.cloud.serviceapi.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("serverapi")
public interface WorldRemoteApi {
    @RequestMapping(name = "/world")
    public Student world(@RequestParam("name") String name);
}
