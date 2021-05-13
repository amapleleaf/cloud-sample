package com.example.nacoshttpproducer.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class SubjectHandler<T> implements InvocationHandler {
    private Class<T> proxyInterface;
    public SubjectHandler(Class<T> proxyInterface){
        this.proxyInterface = proxyInterface;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("selectById")){
            //String result = (String) method.invoke(proxyInterface,args);
            //这里可以得到方法抽象对象来调用真的的查询方法
            return "---selectById---";
        }
        return "---selectByName---";
    }

    public T getProxy(){
       return(T) Proxy.newProxyInstance(proxyInterface.getClassLoader(),new Class[]{proxyInterface},this);
    }
}
