package com.qf.j1906.controller;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: 刘旭
 * Date: 2019/11/1 17:36
 * @Version 1.0
 */
@ControllerAdvice //标识此类用来拦截控制层产生的异常并发出通知
public class ExceptionController {
    @ExceptionHandler(value = UnauthorizedException.class)
    public String defaultErrorHandler(HttpServletRequest request) {
        return "unauth";
    }
}
