package com.immyc.blog.admin.service.impl;

import com.immyc.blog.admin.dao.RoleMapper;
import com.immyc.blog.admin.model.Role;
import com.immyc.blog.admin.model.UserRole;
import com.immyc.blog.admin.service.IRoleService;
import com.immyc.blog.admin.service.IUserRoleService;
import com.immyc.blog.common.IdGen;
import com.immyc.blog.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private IUserRoleService userRoleService;

    @Override
    public void addRole(Role role) {
        role.setId(IdGen.getId());
        role.setCreateTime(System.currentTimeMillis());
        role.setUpdateTime(System.currentTimeMillis());
        role.setIsDeleted(false);
        role.setSort(maxRoleSort()+1);
        this.roleMapper.insert(role);
    }

    @Override
    public void delRole(Long id, Boolean realDelete) {
        Role role = getById(id);
        if (role == null) {
            throw new BusinessException();
        }
        if (realDelete) {
            roleMapper.deleteRole(id);
        } else {
            role.setIsDeleted(true);
            role.setUpdateTime(System.currentTimeMillis());
            this.roleMapper.updateRole(role);
        }
        // 角色删除后，对应的人员角色关系表也要删除
        List<UserRole> userRoles = userRoleService.findUserRoleByRoleId(id);
        if (!userRoles.isEmpty()) {
            List<Long> usrRolesIds = new ArrayList<>(userRoles.size());
            userRoles.forEach(userRole -> {
                usrRolesIds.add(userRole.getId());
            });
            userRoleService.delUserRolePatch(userRoles.toArray(new Long[userRoles.size()]));
        }

    }

    @Override
    public int maxRoleSort() {
        Integer maxSort = roleMapper.maxRoleSort();
        if (maxSort == null) {
            return 0;
        }
        return maxSort;
    }

    @Override
    public Role getById(Long id) {
        return this.roleMapper.getById(id);
    }
}
