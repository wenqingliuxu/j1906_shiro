package com.qf.j1906.service.impl;

import com.qf.j1906.mapper.SysPermissionMapper;
import com.qf.j1906.pojo.SysPermission;
import com.qf.j1906.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 刘旭
 * Date: 2019/11/1 16:58
 * @Version 1.0
 */
@Service
public class SysPermissionServiceImpl implements SysPermissionService {
    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Override
    public List<SysPermission> findPermissionsByLoginName(String loginName) {
        List<SysPermission> permByLoginName = sysPermissionMapper.findPermByLoginName(loginName);
        return permByLoginName;
    }
}
