package com.sample.common.service.imp;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sample.common.dao.SysMenuMapper;
import com.sample.common.dao.SysUserMapper;
import com.sample.common.model.PageRequest;
import com.sample.common.model.SysMenu;
import com.sample.common.model.SysUser;
import com.sample.common.model.UserInfo;
import com.sample.common.service.ISysUserService;
import com.sample.common.util.BaseException;
import com.sample.common.util.EncryptionUtil;
import com.sample.common.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysUserService implements ISysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Value("${jwt.signing.key}")
    private String base64Secret;
    @Override
    public String getAccessToken(SysUser sysUser) {
        SysUser result = sysUserMapper.selectByUserAccount(sysUser.getUserAccount());
        if(result==null) {
            throw  new BaseException("20000", "用户或密码错误");
        }
        if(!EncryptionUtil.sha256Hex(sysUser.getPassword(),result.getSalt()).equals(result.getPassword())){
            throw  new BaseException("20000", "用户或密码错误");
        }

        return JwtTokenUtil.createJWT(new HashMap<String,Object>(){{put("userId",result.getUserId());put("userAccount",result.getUserAccount());put("userName",result.getUserName());}}, base64Secret);
    }

    @Override
    public PageInfo<SysUser> selectUsers(SysUser sysUser, PageRequest pageRequest) {
        //PageHelper.startPage(pageRequest.getPageNum(),pageRequest.getSize());
        if(sysUser == null){
            sysUser = new SysUser();
        }
        //List<SysUser> sysUserList = sysUserMapper.selectUsers(sysUser);
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

    @Override
    public Map<String, Object> getLoginUserInfo(Long userId) {
        List<SysMenu> sysMenuList = sysMenuMapper.selectMenusByUserId(userId);
        List<String> menuCodeList = new ArrayList<>();
        sysMenuList.forEach(oneMenu->menuCodeList.add(oneMenu.getMenuCode()));

        Map<String,Object> userInfo = new HashMap<>();
        userInfo.put("menuCodeList",menuCodeList);
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        userInfo.put("userName",sysUser.getUserName());
        userInfo.put("userAccount",sysUser.getUserAccount());
        return userInfo;
    }
}
