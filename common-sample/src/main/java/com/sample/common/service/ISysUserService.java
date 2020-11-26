package com.sample.common.service;

import com.github.pagehelper.PageInfo;
import com.sample.common.model.PageRequest;
import com.sample.common.model.SysUser;

import java.util.Map;

public interface ISysUserService {
    String getAccessToken(SysUser sysUser);

    PageInfo<SysUser> selectUsers(SysUser sysUser, PageRequest pageRequest);

    int saveSysUser(SysUser sysUser);

    Map<String,Object> getLoginUserInfo(Long userId);
}
