package com.sample.cloud.cloudconsumer.api;

import com.sample.cloud.serviceapi.api.HelloRemoteApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("eureka-producer")
public interface HelloRemoteService extends HelloRemoteApi{

}
