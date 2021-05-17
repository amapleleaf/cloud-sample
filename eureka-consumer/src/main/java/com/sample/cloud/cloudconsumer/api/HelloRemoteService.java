package com.sample.cloud.cloudconsumer.api;

import com.sample.cloud.serviceapi.api.HelloRemoteApi;
import org.springframework.cloud.openfeign.FeignClient;

//@FeignClient("eureka-producer")
@FeignClient(value ="eureka-producer" ,url = "http://www.baidu.com")
public interface HelloRemoteService extends HelloRemoteApi{

}
