package com.immyc.blog.admin.service.impl;

import com.immyc.blog.admin.dao.PermissionMapper;
import com.immyc.blog.admin.model.Permission;
import com.immyc.blog.admin.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public void addPermission(Permission permission) {

    }
}
