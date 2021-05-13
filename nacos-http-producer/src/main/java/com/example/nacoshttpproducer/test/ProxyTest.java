package com.example.nacoshttpproducer.test;

import java.lang.reflect.Proxy;

public class ProxyTest {
    public static void main(String[] args) {
        SubjectHandler<MySubject> subjectHandler = new SubjectHandler(MySubject.class);
        MySubject proxyInstance = (MySubject)Proxy.newProxyInstance(MySubject.class.getClassLoader(),new Class[]{MySubject.class},subjectHandler);
        System.out.println(proxyInstance.selectById());

        System.out.println(subjectHandler.getProxy().selectByName());
    }
}
