package com.sample.common.service;

import com.sample.common.model.PageRequest;
import com.sample.common.model.SysMenu;

import java.util.List;

public interface ISysMenuService {

    List<SysMenu> selectMenus(SysMenu sysMenu, PageRequest pageRequest);

    List<SysMenu> selectMenusByUserId(Long userId);
}
