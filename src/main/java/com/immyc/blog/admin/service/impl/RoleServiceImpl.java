package com.immyc.blog.admin.service.impl;

import com.immyc.blog.admin.dao.RoleMapper;
import com.immyc.blog.admin.model.Role;
import com.immyc.blog.admin.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public void addRole(Role role) {

    }
}
