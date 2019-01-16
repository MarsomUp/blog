package com.immyc.blog.admin.controller;

import com.immyc.blog.admin.model.Role;
import com.immyc.blog.admin.service.IRoleService;
import com.immyc.blog.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 角色控制器
 * @Author: mayc
 * @Date: 2019/01/16 14:20
 */
@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @PostMapping("addRole")
    public Result addRole(Role role) {
        this.roleService.addRole(role);
        return Result.ok();
    }

    @PostMapping("delRole")
    public Result removeRole(Long id) {
        return Result.ok();
    }
}
