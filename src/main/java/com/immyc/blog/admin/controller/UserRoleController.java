package com.immyc.blog.admin.controller;

import com.immyc.blog.admin.service.IUserRoleService;
import com.immyc.blog.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * @Description:
 * @Author: mayc
 * @Date: 2019/01/23 17:11
 */
@RestController
@RequestMapping("user_role")
public class UserRoleController {

    @Autowired
    private IUserRoleService userRoleService;

    @PostMapping("/add")
    public Result addUserRole(@NotNull Long userId, @NotNull String roleIds) {
        userRoleService.addRoleToUser(userId, roleIds);
        return Result.ok();
    }
}
