package com.sample.common.action;

import com.sample.common.model.PageRequest;
import com.sample.common.model.ResponseResult;
import com.sample.common.model.SysRole;
import com.sample.common.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("role")
public class RoleAction {
    @Autowired
    private ISysRoleService sysRoleService;
    @RequestMapping("querySysRoleList")
    public ResponseResult querySysRoleList(SysRole sysRole, PageRequest pageRequest){
        return ResponseResult.success(sysRoleService.selectRoles(sysRole,pageRequest));
    }
}
