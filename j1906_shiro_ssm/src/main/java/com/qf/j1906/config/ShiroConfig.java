package com.qf.j1906.config;

import com.qf.j1906.shiro.MyShiroRealm;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 刘旭
 * Date: 2019/11/1 15:02
 * @Version 1.0
 */
@Configuration
public class ShiroConfig {
    //    创建shiro权限过滤器
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("webSecurityManager") DefaultWebSecurityManager webSecurityManager) {
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
//        配置安全管理器
        filterFactoryBean.setSecurityManager(webSecurityManager);
//        设置鉴权过滤链
        Map<String, String> map = new HashMap();
        map.put("/main", "authc");//管理员主页面需要登录认证
//        map.put("/one","perms[user_edit]");//需要登录后，且拥有user_edit权限才可访问
//        map.put("/two","perms[user_forbidden]");//需要登录后，且拥有user_forbidden权限才可访问
        filterFactoryBean.setFilterChainDefinitionMap(map);
        filterFactoryBean.setLoginUrl("/login");//设置登录页
        filterFactoryBean.setUnauthorizedUrl("/unauth");//权限不足的提示页
        return filterFactoryBean;
    }

    //    创建安全管理器
    @Bean("webSecurityManager")
    public DefaultWebSecurityManager defaultSecurityManager(@Qualifier("myRealm") AuthorizingRealm myRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        给安全管理器配置权限策略
        securityManager.setRealm(myRealm);
        return securityManager;
    }

    //    自定义认证授权的安全策略
    @Bean("myRealm")
    public MyShiroRealm getMyShiroRealm() {
        return new MyShiroRealm();
    }

    /**
     * 开启Shiro注解(如@RequiresRoles,@RequiresPermissions),
     * 需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator和AuthorizationAttributeSourceAdvisor)
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 开启aop注解支持
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager defaultWebSecurityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(defaultWebSecurityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
