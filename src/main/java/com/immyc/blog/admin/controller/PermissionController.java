package com.immyc.blog.admin.controller;

import com.immyc.blog.admin.model.Permission;
import com.immyc.blog.admin.service.IPermissionService;
import com.immyc.blog.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 权限控制器
 * @Author: mayc
 * @Date: 2019/01/16 14:17
 */
@RestController
@RequestMapping("permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @PostMapping("addPermission")
    public Result addPermission(Permission permission) {
        this.permissionService.addPermission(permission);
        return Result.ok();
    }
}
