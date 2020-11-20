package com.sample.common.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.sample.common.model.PageRequest;
import com.sample.common.model.SysUser;

import java.util.List;

public interface ISysUserService {
    String getAccessToken(SysUser sysUser);

    PageInfo<SysUser> selectUsers(SysUser sysUser, PageRequest pageRequest);

    int saveSysUser(SysUser sysUser);
}
