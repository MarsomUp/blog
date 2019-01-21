package com.immyc.blog.admin.controller;

import com.immyc.blog.admin.model.User;
import com.immyc.blog.admin.service.IUserService;
import com.immyc.blog.common.Result;
import com.immyc.blog.common.util.ParamUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequiresPermissions("ZUZHANG:USER_MANAGER")
    @PostMapping("register")
    public Result add(User user) {
        this.userService.addUser(user);
        return Result.ok();
    }

    @PostMapping("disableUser")
    public Result disable(HttpServletRequest request) {
        String id = request.getParameter("id");
        this.userService.disableUser(Long.valueOf(id));
        return Result.ok();
    }

    @PostMapping(name = "findAllUser")
    public Result findAllUser(User user, HttpServletRequest request) {
        Map param = request.getParameterMap();
        Map<String, Object> p = (Map) ParamUtil.parseMapToObject(param, HashMap.class);
        List<User> users = this.userService.findAllUser(p);
        return Result.ok(users);
    }

}
