package com.sample.common.service.imp;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sample.common.dao.SysRoleMapper;
import com.sample.common.dao.SysUserMapper;
import com.sample.common.model.PageRequest;
import com.sample.common.model.SysRole;
import com.sample.common.model.SysUser;
import com.sample.common.service.ISysRoleService;
import com.sample.common.service.ISysUserService;
import com.sample.common.util.BaseException;
import com.sample.common.util.EncryptionUtil;
import com.sample.common.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleService implements ISysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;


    @Override
    public PageInfo<SysRole> selectRoles(SysRole sysRole, PageRequest pageRequest) {
        if(sysRole == null){
            sysRole = new SysRole();
        }
        SysRole finalSysRole = sysRole;
        PageInfo<SysRole> pageResult = PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getSize()).doSelectPageInfo(()->sysRoleMapper.selectRoles(finalSysRole));
        return pageResult;
    }

}
