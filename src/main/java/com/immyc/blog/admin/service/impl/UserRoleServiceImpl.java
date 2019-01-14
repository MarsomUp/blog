package com.immyc.blog.admin.service.impl;

import com.immyc.blog.admin.dao.UserRoleMapper;
import com.immyc.blog.admin.model.Role;
import com.immyc.blog.admin.model.UserRole;
import com.immyc.blog.admin.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements IUserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public void addUserRole(UserRole userRole) {

    }

    @Override
    public List<Role> findUserRolesById(Long id) {
        return null;
    }
}
