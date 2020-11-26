package com.sample.common.configure;

import com.sample.common.model.ResponseResult;
import com.sample.common.model.UserInfo;
import com.sample.common.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationInterceptor  implements HandlerInterceptor {
    @Value("${jwt.signing.key}")
    private String base64Secret;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String accessToke = request.getHeader("Authorization");

        if(!StringUtils.isEmpty(accessToke) && accessToke.split(" ").length>1){
            accessToke = accessToke.split(" ")[1];
        }
        UserInfo uerInfo = JwtTokenUtil.getJWTInfo(accessToke,base64Secret,UserInfo.class);
        request.setAttribute("userInfo", uerInfo);
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
