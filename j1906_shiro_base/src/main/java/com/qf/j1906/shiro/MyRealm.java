package com.qf.j1906.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;

/**
 * @Author: 刘旭
 * Date: 2019/11/1 11:16
 * @Version 1.0
 */
public class MyRealm extends AuthorizingRealm {

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取用户的认证信息
        String principal = (String) principalCollection.getPrimaryPrincipal();
        //通过认证信息查询用户的已分配权限
        String dbPermission = "user:select";
        HashSet<Object> stringPermissions = new HashSet<>();
        stringPermissions.add(dbPermission);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        return authorizationInfo;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//       获取认证信息
        String principal =(String) authenticationToken.getPrincipal();
//        获取凭证信息
//        String credentials = (String) authenticationToken.getCredentials();
//        调用业务方法：根据用户名获取用户信息
        String dbPrincipal=principal;
        String dbcredentials="1234";
        String realName=this.getName();
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(dbPrincipal,dbcredentials,realName);
        return authenticationInfo;
    }
}
