package com.immyc.blog.admin.controller;

import com.immyc.blog.admin.model.User;
import com.immyc.blog.admin.service.IUserService;
import com.immyc.blog.common.RedisUtil;
import com.immyc.blog.common.Result;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @Author: mayc
 * @Date: 2019/01/06 0:03
 */
@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private IUserService userService;
    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("do")
    public Result login(HttpServletRequest request) {
        String loginName = request.getParameter("loginName");
        String pwd = request.getParameter("pwd");
        UsernamePasswordToken token = new UsernamePasswordToken(loginName, pwd);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        User user = userService.getByLoginName(loginName);
        subject.getSession().setAttribute("CURRENT_USER", user);
        return Result.ok();
    }
}
