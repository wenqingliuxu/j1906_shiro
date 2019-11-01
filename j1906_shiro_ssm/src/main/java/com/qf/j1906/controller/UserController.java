package com.qf.j1906.controller;

import com.qf.j1906.vo.UserVo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: 刘旭
 * Date: 2019/11/1 15:24
 * @Version 1.0
 */
@Controller
public class UserController {
    private final Logger logger = LogManager.getLogger(UserController.class);

    //    显示登录页
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginView() {
        return "login";
    }

    //    处理用户登录
    @RequestMapping(value = "/dealLogin", method = RequestMethod.POST)
    public String dealLogin(UserVo userVo) {
        try {
//        认证过程
//        创建令牌
            UsernamePasswordToken token = new UsernamePasswordToken(userVo.getLoginName(), userVo.getPassword());
//        获取shiro主体对象
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            if (subject.isAuthenticated()) {
                return "main";
            }
        } catch (AuthenticationException e) {
//            e.printStackTrace();
            logger.error("认证失败！");
        }
        return "login";
    }

    //    显示主视图
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String showMainView() {
        return "main";
    }

    //    显示无权访问
    @RequestMapping("/unauth")
    public String showAuth() {
        return "unauth";
    }

    //    显示功能1页面
    @RequiresPermissions(value = {"user_edit"})
    @RequestMapping("/one")
    public String showOne() {
        return "one";
    }

    //    显示功能2页面
    @RequiresPermissions(value = {"user_forbidden"})
    @RequestMapping("/two")
    public String showTwo() {
        return "two";
    }

    @RequestMapping("/logOut")
    public String logOut() {
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
//            清除业务会话信息

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:login";
    }
}
