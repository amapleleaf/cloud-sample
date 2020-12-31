package com.example.nacosgateway.config;

import com.example.nacosgateway.model.ResponseCodeEnum;
import com.example.nacosgateway.model.ResponseResult;
import com.example.nacosgateway.util.JsonUtils;
import com.example.nacosgateway.util.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {
    private static final String AUTHORIZE_TOKEN = "access_token";
    @Value("${jwt.base64.security}")
    private String base64Security;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        ServerHttpResponse serverHttpResponse = exchange.getResponse();
        HttpHeaders headers = serverHttpRequest.getHeaders();
        String access_token = headers.getFirst(AUTHORIZE_TOKEN);
        if (access_token == null) {
            access_token = serverHttpRequest.getQueryParams().getFirst(AUTHORIZE_TOKEN);
        }


        ServerHttpResponse response = exchange.getResponse();
        if (StringUtils.isEmpty(access_token) ) {
            /*response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();*/
            return getVoidMono(serverHttpResponse,ResponseCodeEnum.TOKEN_MISSION);
        }
        String userId = "";
        try {
            userId =JwtTokenUtil.getUserId(access_token,base64Security);
        } catch (ExpiredJwtException e) {
           return getVoidMono(serverHttpResponse,ResponseCodeEnum.TOKEN_EXPIRED);
        } catch (Exception e) {
            return getVoidMono(serverHttpResponse,ResponseCodeEnum.TOKEN_INVALID);
        }
         /*String authToken = stringRedisTemplate.opsForValue().get(uid);
        if (authToken == null || !authToken.equals(token)) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }*/
        ServerHttpRequest mutableReq = serverHttpRequest.mutate().header("userId", userId).build();
        ServerWebExchange mutableExchange = exchange.mutate().request(mutableReq).build();
        return chain.filter(mutableExchange);
    }

    private Mono<Void> getVoidMono(ServerHttpResponse serverHttpResponse, ResponseCodeEnum responseCodeEnum) {
         serverHttpResponse.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
         ResponseResult responseResult = ResponseResult.error(responseCodeEnum.getCode(), responseCodeEnum.getMessage());
         DataBuffer dataBuffer = serverHttpResponse.bufferFactory().wrap(JsonUtils.obj2String(responseResult).getBytes());
         return serverHttpResponse.writeWith(Flux.just(dataBuffer));
    }

    @Override
    public int getOrder() {
        return 0;
    }

}
