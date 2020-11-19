package com.sample.common.action;

import com.sample.common.model.PageRequest;
import com.sample.common.model.ResponseResult;
import com.sample.common.model.SysUser;
import com.sample.common.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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

    @RequestMapping("querySysUserList")
    public ResponseResult querySysUserList(SysUser sysUser, PageRequest pageRequest){
        return ResponseResult.success(sysUserService.selectUsers(sysUser,pageRequest));
    }

}
