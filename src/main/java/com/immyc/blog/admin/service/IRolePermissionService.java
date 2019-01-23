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
     * @param roleId
     * @return
     */
    List<Permission> findRolePermissionByRoleId(Long roleId);

    /**
     * 批量给角色授予权限
     * @param roleId
     * @param permIds
     */
    void addRolePerm(Long roleId, Long[] permIds);

    /**
     * 取消给角色授予的权限
     * @param rolePermIds
     */
    void delRolePerm(Long[] rolePermIds);

    /**
     * 根据权限ID获取角色权限关系数据
     * @param permissionId
     * @return
     */
    List<RolePermission> findByPermissionId(Long permissionId);
}
