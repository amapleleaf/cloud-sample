package com.sample.cloud.cloudconsumer.api;

import com.sample.cloud.serviceapi.api.HelloRemoteApi;
import com.sample.cloud.serviceapi.api.WorldRemoteApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("eureka-world")
public interface WorldRemoteService extends WorldRemoteApi {

}
