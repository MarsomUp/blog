package com.immyc.blog.admin.service.impl;

import com.immyc.blog.admin.dao.PermissionMapper;
import com.immyc.blog.admin.model.Permission;
import com.immyc.blog.admin.model.RolePermission;
import com.immyc.blog.admin.service.IPermissionService;
import com.immyc.blog.admin.service.IRolePermissionService;
import com.immyc.blog.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private IRolePermissionService rolePermissionService;

    @Override
    public void addPermission(Permission permission) {
        this.permissionMapper.insert(permission);
    }

    @Override
    public void deletePermission(Long id, Boolean realDelete) {
        Permission permission = permissionMapper.getById(id);
        if (permission == null) {
            throw new BusinessException();
        }
        if (realDelete) {
            permissionMapper.deletePermission(id);
        } else {
            permission.setIsDeleted(true);
            permission.setUpdateTime(System.currentTimeMillis());
            this.permissionMapper.updatePermission(permission);
        }
        // 权限删除后，角色权限关系表也要删除
        List<RolePermission> rolePermissions = rolePermissionService.findByPermissionId(id);

        if (!rolePermissions.isEmpty()) {
            List<Long> rolePermIds = new ArrayList<>(10);
            Long[] rpids = new Long[rolePermissions.size()];
            rolePermissions.forEach(rolePerm -> {
                rolePermIds.add(rolePerm.getId());
            });
            rolePermIds.toArray(rpids);
            rolePermissionService.delRolePerm(rpids);
        }

    }
}
