package com.immyc.blog.admin.service.impl;

import com.immyc.blog.admin.dao.UserRoleMapper;
import com.immyc.blog.admin.model.Role;
import com.immyc.blog.admin.model.UserRole;
import com.immyc.blog.admin.service.IUserRoleService;
import com.immyc.blog.common.IdGen;
import com.immyc.blog.common.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserRoleServiceImpl implements IUserRoleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserRoleServiceImpl.class);

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public void addUserRole(UserRole userRole) {

    }

    @Override
    public List<Role> findUserRolesByUserId(Long userId) {
        return userRoleMapper.findUserRoleByUserId(userId);
    }

    @Override
    public void addUserRolePatch(List<UserRole> userRoles) {
        if (!userRoles.isEmpty()) {
            this.userRoleMapper.addUserRolePatch(userRoles);
        }
    }

    @Override
    public void delUserRolePatch(Long... userRoleIds) {
        List<Long> ids = Arrays.asList(userRoleIds);
        this.userRoleMapper.deleteUserRolePatch(ids);
    }

    @Override
    public void addRoleToUser(Long userId, Long[] roleIds) {
        if (userId == null) {
            throw new BusinessException();
        }
        List<UserRole> roles = new ArrayList<>(10);
        for (Long roleId : roleIds) {
            UserRole userRole = new UserRole();
            userRole.setId(IdGen.getId());
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);
            userRole.setCreateTime(System.currentTimeMillis());
            roles.add(userRole);
        }
        addUserRolePatch(roles);
    }

    @Override
    public void addRoleToUser(Long userId, String roleIds) {
        if (userId == null || StringUtils.isBlank(roleIds)) {
            throw new BusinessException();
        }
        String[] roleIdArr = roleIds.split(",");
        Long[] rids = Arrays.stream(roleIdArr).map(i -> Long.valueOf(i)).toArray(Long[]::new);
        this.addRoleToUser(userId, rids);
    }

    @Override
    public List<UserRole> findUserRoleByRoleId(Long roleId) {
        return this.userRoleMapper.findUserRoleByRoleId(roleId);
    }
}
