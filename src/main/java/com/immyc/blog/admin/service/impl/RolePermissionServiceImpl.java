package com.immyc.blog.admin.service.impl;

import com.immyc.blog.admin.dao.RolePermissionMapper;
import com.immyc.blog.admin.model.Permission;
import com.immyc.blog.admin.model.RolePermission;
import com.immyc.blog.admin.service.IRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
