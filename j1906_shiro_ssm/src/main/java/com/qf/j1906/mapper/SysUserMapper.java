package com.qf.j1906.mapper;

import com.qf.j1906.pojo.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: 刘旭
 * Date: 2019/11/1 14:38
 * @Version 1.0
 */
@Mapper
@Repository
public interface SysUserMapper {
    public SysUser findSysUserByLoginName(@Param("loginName") String loginName);
}
