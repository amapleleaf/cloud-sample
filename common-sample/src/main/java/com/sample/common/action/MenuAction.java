package com.sample.common.action;

import com.sample.common.model.PageRequest;
import com.sample.common.model.ResponseResult;
import com.sample.common.model.SysMenu;
import com.sample.common.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("menu")
public class MenuAction {
    @Autowired
    private ISysMenuService sysMenuService;
    @RequestMapping("querySysMenuList")
    public ResponseResult querySysUserList(SysMenu sysMenu, PageRequest pageRequest){
        return ResponseResult.success(sysMenuService.selectMenus(sysMenu,pageRequest));
    }
}
