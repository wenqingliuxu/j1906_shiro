package com.qf.j1906.service.impl;

import com.qf.j1906.mapper.SysUserMapper;
import com.qf.j1906.pojo.SysUser;
import com.qf.j1906.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 刘旭
 * Date: 2019/11/1 14:55
 * @Version 1.0
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper userMapper;

    @Override
    public SysUser findUserByLoginName(String name) {
        SysUser user = userMapper.findSysUserByLoginName(name);
        return user;
    }
}
