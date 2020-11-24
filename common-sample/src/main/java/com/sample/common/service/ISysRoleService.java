package com.sample.common.service;

import com.github.pagehelper.PageInfo;
import com.sample.common.model.PageRequest;
import com.sample.common.model.SysRole;

public interface ISysRoleService {

    PageInfo<SysRole> selectRoles(SysRole sysRole, PageRequest pageRequest);

}
