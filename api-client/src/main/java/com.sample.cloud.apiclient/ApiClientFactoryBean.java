package com.sample.cloud.apiclient;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Proxy;

public class ApiClientFactoryBean implements FactoryBean<Object> , ApplicationContextAware {
    private Class<?> target ;
    private String name;
    private ApplicationContext applicationContext;
    private Class<? extends ApiClientInvoker> apiClientIncoker;

    @Override
    public Object getObject() throws Exception {
        return Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                new Class[]{target},
                new ApiClientInvocationHandler(this.target,applicationContext,apiClientIncoker));
    }

    @Override
    public Class<?> getObjectType() {
        return target;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public Class<?> getTarget() {
        return target;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<? extends ApiClientInvoker> getApiClientIncoker() {
        return apiClientIncoker;
    }

    public void setApiClientIncoker(Class<? extends ApiClientInvoker> apiClientIncoker) {
        this.apiClientIncoker = apiClientIncoker;
    }

    public void setTarget(Class<?> target) {
        this.target = target;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

}
