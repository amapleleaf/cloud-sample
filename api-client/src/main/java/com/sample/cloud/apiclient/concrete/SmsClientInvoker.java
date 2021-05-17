package com.sample.cloud.apiclient.concrete;

import com.sample.cloud.apiclient.ApiClientInvoker;

import java.lang.reflect.Method;

/**
 *  自定义invoker逻辑
 * 1、自定义的Invoker,需要将具体的invoker注册到上下文
 * 2、yml中配置
 * apiclient:
 *   invoker:
 *     SmsService: com.sample.cloud.cloudconsumer.apiclient.SmsClientInvoker
 */
public class SmsClientInvoker implements ApiClientInvoker {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {

        return "-----------SmsClientInvoker--------------";
    }
}
