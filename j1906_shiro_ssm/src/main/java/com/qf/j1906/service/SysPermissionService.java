package com.qf.j1906.service;

import com.qf.j1906.pojo.SysPermission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: 刘旭
 * Date: 2019/11/1 16:44
 * @Version 1.0
 */
public interface SysPermissionService {
    public List<SysPermission> findPermissionsByLoginName(String loginName);
}
