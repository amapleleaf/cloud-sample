package com.sample.cloud.apiclient;

import java.lang.reflect.Method;

public interface ApiClientInvoker {
    Object invoke(Object proxy, Method method, Object[] args);
}
