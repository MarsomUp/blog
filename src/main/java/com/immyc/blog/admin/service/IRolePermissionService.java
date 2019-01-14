package com.immyc.blog.admin.service;

import com.immyc.blog.admin.model.Permission;
import com.immyc.blog.admin.model.RolePermission;

import java.util.List;

public interface IRolePermissionService {

    /**
     * 新增角色权限
     * @param rolePermission
     */
    void addRolePermission(RolePermission rolePermission);

    /**
     * 根据角色id获取角色权限
     * @param id
     * @return
     */
    List<Permission> findRolePermissionByRoleId(Long id);
}
