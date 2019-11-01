package com.qf.j1906.service;

import com.qf.j1906.pojo.SysUser;

/**
 * @Author: 刘旭
 * Date: 2019/11/1 14:54
 * @Version 1.0
 */
public interface SysUserService {
    //    根据登录名称查询用户信息
    public SysUser findUserByLoginName(String name);
}
