package com.qf.j1906;

import static org.junit.Assert.assertTrue;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest
{
//    使用自定义安全策略
    @Test
    public void testRealm(){
        try {
            IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
            SecurityManager securityManager = factory.getInstance();
            SecurityUtils.setSecurityManager(securityManager);
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "1234");
            subject.login(token);
            if (subject.isAuthenticated()){
                System.out.println("认证成功");
                if(subject.isPermitted("user:select")){
                    System.out.println("用户张三拥有select权限");
                }
            }else{
                System.out.println("认证失败");
            }
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
    }

//授权
    @Test
    public void testSecond(){
        try {
            //使用ini资源初始化安全管理器工厂
            IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro-permission.ini");
            //创建安全管理器
            SecurityManager securityManager = factory.getInstance();
            //使用安全管理器
            SecurityUtils.setSecurityManager(securityManager);
            //获取主体（认证信息+凭证信息）对象
            Subject subject = SecurityUtils.getSubject();
            //创建基于用户名+密码的令牌信息（对象）
            UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "123");
            //调用主体的login方法进行认证
            subject.login(token);
            if(subject.isAuthenticated()){
                if (subject.hasRole("role1")) {
                    System.out.println("zhangsan has:role1");
                }
                if(!subject.hasRole("role4")){
                    System.out.println("zhangsan has not:role4");
                }
                if(subject.isPermitted("user:select")){
                    System.out.println("zhangsan has not :user select permission");
                }
                if(!subject.isPermitted("user:query")){
                    System.out.println("zhangsan has not :user query permission");
                }
            }
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
    }

    //认证
    @Test
    public void testFirst() {
        try {
            IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro-first.ini");
            SecurityManager securityManager= (SecurityManager) factory.getInstance();
            SecurityUtils.setSecurityManager(securityManager);
            Subject subject=SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "123");
            subject.login(token);
            if(subject.isAuthenticated()){
                System.out.println("认证成功");
            }else{
                System.out.println("认证失败");
            }
        } catch (UnknownAccountException e) {
            System.out.println("用户不存在");
            e.printStackTrace();
        }catch (IncorrectCredentialsException e){
            System.out.println("密码不正确");
        }catch (AuthenticationException e){
            System.out.println("认证无效");
        }
    }
}
