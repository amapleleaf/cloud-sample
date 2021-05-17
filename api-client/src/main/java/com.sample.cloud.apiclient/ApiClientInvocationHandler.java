package com.sample.cloud.apiclient;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ApiClientInvocationHandler implements InvocationHandler {
    private ApplicationContext applicationContext;
    private Class<?> target;
    private Class<? extends ApiClientInvoker> apiClientIncoker;
    public ApiClientInvocationHandler(Class<?> target, ApplicationContext applicationContext,Class<? extends ApiClientInvoker> apiClientIncoker){
        this.target = target;
        this.applicationContext = applicationContext;
        this.apiClientIncoker = apiClientIncoker;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ("equals".equals(method.getName())) {
            try {
                Object otherHandler =
                        args.length > 0 && args[0] != null ? Proxy.getInvocationHandler(args[0]) : null;
                return equals(otherHandler);
            } catch (IllegalArgumentException e) {
                return false;
            }
        } else if ("hashCode".equals(method.getName())) {
            return target.hashCode();
        } else if ("toString".equals(method.getName())) {
            return target.toString();
        }
        return applicationContext.getBean(apiClientIncoker).invoke(proxy,method,args);
    }
}
