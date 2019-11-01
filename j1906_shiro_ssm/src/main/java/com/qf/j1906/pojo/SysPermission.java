package com.qf.j1906.pojo;

import lombok.Data;

/**
 * @Author: 刘旭
 * Date: 2019/11/1 16:45
 * @Version 1.0
 */
@Data
public class SysPermission {
    private int permissionId;//权限Id
    private String perName;//权限名
    private String menuName;//菜单名
    private String menuType;//菜单分类
    private String menuUrl;//菜单Url
    private String menuCode;//菜单编码
    private String parentCode;//父编码
    private String perDesc;//权限描述
    private int ifVilid;//权限是否有效
}
