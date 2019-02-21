package com.immyc.blog.admin.controller;

import com.immyc.blog.admin.model.User;
import com.immyc.blog.admin.service.IUserService;
import com.immyc.blog.common.RedisUtil;
import com.immyc.blog.common.Result;
import com.immyc.blog.common.constants.MyConstants;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

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
        try {
            subject.login(token);
        } catch (UnknownAccountException ex) {
            LOGGER.error("用户名未找到", ex);
        } catch (IncorrectCredentialsException ex) {
            LOGGER.error("用户名密码不匹配", ex);
        } catch (AuthenticationException e) {
            LOGGER.error("其他的错误", e);
        }
        User user = userService.getByLoginName(loginName);
        subject.getSession().setAttribute(MyConstants.CURRENT_USER, user);
        return Result.ok("登陆成功！");
    }
}
