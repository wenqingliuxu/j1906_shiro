package com.qf.j1906.mapper;

import com.qf.j1906.pojo.SysPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: 刘旭
 * Date: 2019/11/1 16:51
 * @Version 1.0
 */
@Mapper
@Repository
public interface SysPermissionMapper {
    public List<SysPermission> findPermByLoginName(@Param("name")String name);
}
