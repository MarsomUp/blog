package com.immyc.blog.admin.service.impl;

import com.immyc.blog.admin.dao.UserMapper;
import com.immyc.blog.admin.model.User;
import com.immyc.blog.admin.model.UserRole;
import com.immyc.blog.admin.service.IUserRoleService;
import com.immyc.blog.admin.service.IUserService;
import com.immyc.blog.common.IdGen;
import com.immyc.blog.common.exception.BusinessException;
import com.immyc.blog.common.util.PwdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PwdUtil pwdUtil;
    @Autowired
    private IUserRoleService userRoleService;

    @Override
    public void addUser(User user) {
        if (user == null) {
            throw new NullPointerException();
        }
        user.setId(IdGen.getId());
        user.setCategory(0);
        user.setStatus(0);
        user.setIsDeleted(false);
        user.setUpdateTime(System.currentTimeMillis());
        user.setCreateTime(System.currentTimeMillis());
        pwdUtil.encryptPassword(user);
        this.userMapper.insert(user);
    }

    @Override
    public boolean login(String loginName, String pwd) {
        return false;
    }

    @Override
    public User getByLoginName(String loginName) {
        User user = this.userMapper.getByLoginName(loginName);
        return user;
    }

    @Override
    public void disableUser(Long id) {
        User user = getById(id);
        if (user == null) {
            throw new BusinessException();
        }
        user.setIsDeleted(true);
        user.setUpdateTime(System.currentTimeMillis());
    }

    public User getById(Long id) {
        return this.userMapper.getById(id);
    }

    @Override
    public List<User> findAllUser(Map<String, Object> param) {
        Map<String, Object> params = new HashMap(7);

        return this.userMapper.selectAll(param);
    }


}
