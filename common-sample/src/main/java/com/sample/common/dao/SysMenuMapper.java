package com.sample.common.dao;

import com.sample.common.model.SysMenu;

import java.util.List;

public interface SysMenuMapper {
    int deleteByPrimaryKey(Long menuId);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(Long menuId);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);

    List<SysMenu> selectMenus(SysMenu sysMenu);

    List<SysMenu> selectMenusByUserId(Long userId);
}