package com.sample.common.service.imp;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sample.common.dao.SysUserMapper;
import com.sample.common.model.PageRequest;
import com.sample.common.model.SysUser;
import com.sample.common.service.ISysUserService;
import com.sample.common.util.BaseException;
import com.sample.common.util.EncryptionUtil;
import com.sample.common.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserService implements ISysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    private String base64Secret="MDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjYyN2I0ZjY=";
    @Override
    public String getAccessToken(SysUser sysUser) {
        SysUser result = sysUserMapper.selectByUserAccount(sysUser.getUserAccount());
        if(result==null) {
            throw  new BaseException("20000", "用户或密码错误");
        }
        if(!EncryptionUtil.sha256Hex(sysUser.getPassword(),result.getSalt()).equals(result.getPassword())){
            throw  new BaseException("20000", "用户或密码错误");
        }
        return JwtTokenUtil.createJWT(result.getUserId()+"", base64Secret);
    }

    @Override
    public PageInfo<SysUser> selectUsers(SysUser sysUser, PageRequest pageRequest) {
        PageHelper.startPage(pageRequest.getPageNum(),pageRequest.getSize());
        if(sysUser == null){
            sysUser = new SysUser();
        }
        List<SysUser> sysUserList = sysUserMapper.selectUsers(sysUser);
        //PageInfo<SysUser> pageInfo = new PageInfo<>(sysUserList);
        SysUser finalSysUser = sysUser;
        PageInfo<SysUser> pageResult = PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getSize()).doSelectPageInfo(new ISelect() {
            @Override
            public void doSelect() {
                sysUserMapper.selectUsers(finalSysUser);
            }
        });
        return pageResult;
    }

    @Override
    public int saveSysUser(SysUser sysUser){
        sysUser.setSalt("123321");
        return sysUserMapper.insert(sysUser);
    }

}
