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

    /**
     * 批量给用户授予角色
     * @param userRoles
     */
    void addUserRolePatch(List<UserRole> userRoles);

    /**
     * 取消给用户授予的角色
     * @param userRoleIds
     */
    void delUserRolePatch(Long[] userRoleIds);

    /**
     * 给用户授予角色
     * @param userId
     * @param roleIds
     */
    void addRoleToUser(Long userId, Long[] roleIds);

    /**
     * 根据角色获取角色权限关系表数据
     * @param roleId
     * @return
     */
    List<UserRole> findUserRoleByRoleId(Long roleId);


}
