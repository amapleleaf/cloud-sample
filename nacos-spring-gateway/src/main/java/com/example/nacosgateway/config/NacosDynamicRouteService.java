package com.example.nacosgateway.config;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
@Component
public class NacosDynamicRouteService {
    public static final Logger log = LoggerFactory.getLogger(NacosDynamicRouteService.class);
    private String dataId = "gateway-router";

    private String group = "DEFAULT_GROUP";

    @Value("${spring.cloud.nacos.server-addr}")
    private String serverAddr;

    @Autowired
    private RouteDefinitionWriter routeDefinitionWriter;

    @PostConstruct
    public void initRoute() {
        try {
            ConfigService configService = NacosFactory.createConfigService(serverAddr);
            String content = configService.getConfig(dataId, "DEFAULT_GROUP", 5000);
            log.info("初始化网关路由开始");
            updateRoute(content);
            log.info("初始化网关路由完成");

            //开户监听，实现动态
            configService.addListener(dataId, "DEFAULT_GROUP", new Listener() {
                @Override
                public void receiveConfigInfo(String configInfo) {
                    updateRoute(configInfo);
                }

                @Override
                public Executor getExecutor() {
                    return null;
                }
            });
        } catch (NacosException e) {
            log.error("加载路由出错：{}", e.getErrMsg());
        }
    }

    public void updateRoute(String content) {
        log.info("路由配置：{}",content);
        Yaml yaml = new Yaml();
        GatewayRouteList gatewayRouteList = yaml.loadAs(content, GatewayRouteList.class);
        gatewayRouteList.getRoutes().forEach(route -> {
            log.info("加载路由：{},{}", route.getId(), route);
            routeDefinitionWriter.save(Mono.just(route)).subscribe();
        });
    }

    public static class GatewayRouteList{
        private List<RouteDefinition> routes;

        public List<RouteDefinition> getRoutes() {
            return routes;
        }

        public void setRoutes(List<RouteDefinition> routes) {
            this.routes = routes;
        }
    }
}
