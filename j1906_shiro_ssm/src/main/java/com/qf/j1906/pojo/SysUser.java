package com.qf.j1906.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author: 刘旭
 * Date: 2019/11/1 14:34
 * @Version 1.0
 */
@Data
public class SysUser {
   private int userid;
   private String loginName;
   private String password;
   private int state;
   @DateTimeFormat(pattern = "yyyy-MM-dd")
   private Date createTime;
   private String realname;
}
