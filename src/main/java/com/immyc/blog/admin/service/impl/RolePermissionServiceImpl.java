package com.immyc.blog.admin.service.impl;

import com.immyc.blog.admin.dao.RolePermissionMapper;
import com.immyc.blog.admin.model.Permission;
import com.immyc.blog.admin.model.RolePermission;
import com.immyc.blog.admin.service.IRolePermissionService;
import com.immyc.blog.common.IdGen;
import com.immyc.blog.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class RolePermissionServiceImpl implements IRolePermissionService {

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public void addRolePermission(RolePermission rolePermission) {

    }

    @Override
    public List<Permission> findRolePermissionByRoleId(Long id) {
        return null;
    }

    @Override
    public void addRolePerm(Long roleId, Long[] permIds) {
        if (roleId == null)
            throw new BusinessException();
        List<RolePermission> rolePermissions = new ArrayList<>(10);
        for (Long l : permIds) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setId(IdGen.getId());
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(l);
            rolePermission.setCreateTime(System.currentTimeMillis());
            rolePermissions.add(rolePermission);
        }
        if (!rolePermissions.isEmpty()) {
            this.rolePermissionMapper.addRolePermPatch(rolePermissions);
        }
    }

    @Override
    public void delRolePerm(Long[] rolePermIds) {
        List<Long> ids = Arrays.asList(rolePermIds);
        this.rolePermissionMapper.delRolePermPatch(ids);
    }

    @Override
    public List<RolePermission> findByPermissionId(Long permissionId) {
        return null;
    }
}
