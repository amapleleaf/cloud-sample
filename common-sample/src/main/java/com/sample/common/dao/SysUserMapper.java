package com.sample.common.dao;

import com.sample.common.model.SysUser;

import java.util.List;

public interface SysUserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    SysUser  selectByUserAccount(String userAccount);

    List<SysUser> selectUsers(SysUser sysUser);
}