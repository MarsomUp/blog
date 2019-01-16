package com.immyc.blog.admin.controller;

import com.immyc.blog.admin.model.User;
import com.immyc.blog.admin.service.IUserService;
import com.immyc.blog.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("register")
    @ResponseBody
    public Result add(User user) {
        this.userService.addUser(user);
        return Result.ok();
    }

    @PostMapping("disableUser")
    @ResponseBody
    public Result disable(HttpServletRequest request) {
        String id = request.getParameter("id");
        this.userService.disableUser(Long.valueOf(id));
        return Result.ok();
    }

}
