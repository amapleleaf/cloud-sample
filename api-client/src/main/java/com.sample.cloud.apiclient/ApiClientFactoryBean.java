package com.sample.cloud.apiclient;

import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Proxy;

public class ApiClientFactoryBean implements FactoryBean<Object> {
    private final Class<?> type ;
    private String name;
    public ApiClientFactoryBean(Class<?> type,String name) {
        this.type = type;
        this.name = name;
    }

    @Override
    public Object getObject() throws Exception {
        return Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                new Class[]{type},
                new SmsClientHandler());
    }

    @Override
    public Class<?> getObjectType() {
        return type;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
