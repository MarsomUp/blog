package com.immyc.blog.admin.service;

import com.immyc.blog.admin.model.Role;

public interface IRoleService {

    /**
     * 新增角色
     * @param role
     */
    void addRole(Role role);

    /**
     * 删除角色
     * @param id
     * @param realDelete false:逻辑删除 true:物理删除
     */
    void delRole(Long id, Boolean realDelete);

    /**
     * 获取角色的目前最大序号
     * @return
     */
    int maxRoleSort();

    /**
     * 根据ID获取角色信息
     * @param id
     * @return
     */
    Role getById(Long id);
}
