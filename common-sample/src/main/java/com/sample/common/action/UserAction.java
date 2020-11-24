package com.sample.common.action;

import com.sample.common.model.PageRequest;
import com.sample.common.model.ResponseResult;
import com.sample.common.model.SysUser;
import com.sample.common.service.ISysUserService;
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
    @RequestMapping("accesstoken")
    public ResponseResult getAccessToken(SysUser sysUser){
         String accessToke = sysUserService.getAccessToken(sysUser);
         return ResponseResult.success(new HashMap<String,String>(){{put("accessToke",accessToke);}});
    }

    @RequestMapping("getLoginUserInfo")
    public ResponseResult getLoginUserInfo(@RequestHeader("Authorization") String accessToke){
        if(!StringUtils.isEmpty(accessToke) && accessToke.split(" ").length>1){
            accessToke = accessToke.split(" ")[1];
        }

        return ResponseResult.success(sysUserService.getLoginUserInfo(accessToke));
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
