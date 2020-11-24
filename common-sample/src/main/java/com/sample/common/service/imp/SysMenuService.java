package com.sample.common.service.imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sample.common.dao.SysMenuMapper;
import com.sample.common.model.PageRequest;
import com.sample.common.model.SysMenu;
import com.sample.common.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysMenuService implements ISysMenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper;


    @Override
    public List<SysMenu> selectMenus(SysMenu sysMenu, PageRequest pageRequest) {
        if(sysMenu == null){
            sysMenu = new SysMenu();
        }
        return sysMenuMapper.selectMenus(sysMenu);
    }

    @Override
    public List<SysMenu> selectMenusByUserId(Long userId){
        return sysMenuMapper.selectMenusByUserId(userId);
    }
}
