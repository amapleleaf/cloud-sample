package com.sample.cloud.cloudconsumer.apiclient;

import com.sample.cloud.apiclient.ApiClientInvoker;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
@Component
public class SmsClientInvoker implements ApiClientInvoker {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {

        return "-----------SmsClientInvoker--------------";
    }
}
