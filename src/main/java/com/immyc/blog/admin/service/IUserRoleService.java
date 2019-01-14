package com.immyc.blog.admin.service;

import com.immyc.blog.admin.model.Role;
import com.immyc.blog.admin.model.UserRole;

import java.util.List;

public interface IUserRoleService {

    /**
     * 新增用户角色
     * @param userRole
     */
    void addUserRole(UserRole userRole);

    /**
     * 根据用户ID获取用户角色
     * @param id
     * @return
     */
    List<Role> findUserRolesById(Long id);
}
