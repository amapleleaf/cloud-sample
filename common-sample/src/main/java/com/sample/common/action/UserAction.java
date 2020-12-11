package com.sample.common.action;

import com.sample.common.model.LogMessage;
import com.sample.common.model.PageRequest;
import com.sample.common.model.ResponseResult;
import com.sample.common.model.SysUser;
import com.sample.common.model.UserInfo;
import com.sample.common.service.ISysUserService;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping("user")
public class UserAction {
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @RequestMapping("accesstoken")
    public ResponseResult getAccessToken(SysUser sysUser){
         String accessToke = sysUserService.getAccessToken(sysUser);
         return ResponseResult.success(new HashMap<String,String>(){{put("accessToke",accessToke);}});
    }

    /**
     *
     * @param userInfo
     * @return
     */
    @RequestMapping("getLoginUserInfo")
    public ResponseResult getLoginUserInfo(UserInfo userInfo){
        return ResponseResult.success(sysUserService.getLoginUserInfo(userInfo.getUserId()));
    }

    @RequestMapping("querySysUserList")
    public ResponseResult querySysUserList(SysUser sysUser, PageRequest pageRequest){
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LogMessage loMessage = new LogMessage(UUID.randomUUID().toString(),String.format("操作员：%s ,操作：%s，时间：%s","我是666","用户列表查询",createTime));
        rabbitTemplate.convertAndSend("LogDirectExchange", "LogDirectRouting",loMessage);
        return ResponseResult.success(sysUserService.selectUsers(sysUser,pageRequest));
    }

    @RequestMapping("saveSysUser")
    public ResponseResult saveSysUsert(SysUser sysUser){
        return ResponseResult.success(sysUserService.saveSysUser(sysUser));
    }



}
