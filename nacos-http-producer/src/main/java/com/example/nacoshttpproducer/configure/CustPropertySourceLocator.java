package com.example.nacoshttpproducer.configure;

import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;
import java.util.Map;

public class CustPropertySourceLocator implements PropertySourceLocator {

    @Override
    public PropertySource<?> locate(Environment environment) {
        //可以从自己的配置中心获取数据
        Map<String, Object> configs =new HashMap<>();
        configs.put("metric.post.url","http://baidu.com");
        return new MapPropertySource("MY_CONFIG_CENTER",configs);
    }
}
