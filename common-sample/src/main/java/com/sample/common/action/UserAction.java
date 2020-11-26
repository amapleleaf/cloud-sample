package com.sample.common.action;

import com.sample.common.model.PageRequest;
import com.sample.common.model.ResponseResult;
import com.sample.common.model.SysUser;
import com.sample.common.model.UserInfo;
import com.sample.common.service.ISysUserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

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

    @RequestMapping("getLoginUserInfo")
    public ResponseResult getLoginUserInfo(UserInfo userInfo){
        return ResponseResult.success(sysUserService.getLoginUserInfo(userInfo.getUserId()));
    }

    @RequestMapping("querySysUserList")
    public ResponseResult querySysUserList(SysUser sysUser, PageRequest pageRequest){
        return ResponseResult.success(sysUserService.selectUsers(sysUser,pageRequest));
    }

    @RequestMapping("saveSysUser")
    public ResponseResult saveSysUsert(SysUser sysUser){
        return ResponseResult.success(sysUserService.saveSysUser(sysUser));
    }



}
