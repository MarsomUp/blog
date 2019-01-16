package com.immyc.blog.admin.service;

import com.immyc.blog.admin.model.Permission;

public interface IPermissionService {

    /**
     * 新增角色
     * @param permission
     */
    void addPermission(Permission permission);

    /**
     * 删除角色
     * @param id
     * @param realDelete
     */
    void deletePermission(Long id, Boolean realDelete);
}
