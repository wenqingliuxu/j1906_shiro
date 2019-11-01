package com.qf.j1906.shiro;

import com.qf.j1906.pojo.SysPermission;
import com.qf.j1906.pojo.SysUser;
import com.qf.j1906.service.SysPermissionService;
import com.qf.j1906.service.SysUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: 刘旭
 * Date: 2019/11/1 14:57
 * @Version 1.0
 */

public class MyShiroRealm extends AuthorizingRealm {
    @Autowired
    private SysUserService userService;
    @Autowired
    private SysPermissionService sysPermissionService;

    //    授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String principal = (String) principalCollection.getPrimaryPrincipal();
//        根据用户名查询用户拥有的权限清单
        List<SysPermission> sysPermissions = sysPermissionService.findPermissionsByLoginName(principal);
        if (sysPermissions != null) {
//            权限去重
            Set<String> perms = new HashSet<>();
            for (SysPermission perm : sysPermissions) {
                perms.add(perm.getPerName());
            }
            SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//            授权
            authorizationInfo.setStringPermissions(perms);
            return authorizationInfo;
        }
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//        获取用户信息
        String principal = (String) token.getPrincipal();
        SysUser sysUser = userService.findUserByLoginName(principal);
        if (sysUser != null) {
            String pwd = sysUser.getPassword();
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(principal, pwd, this.getName());
            return simpleAuthenticationInfo;
        }

        return null;
    }
}
