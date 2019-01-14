package com.immyc.blog.admin.service.impl;

import com.immyc.blog.admin.dao.UserMapper;
import com.immyc.blog.admin.model.User;
import com.immyc.blog.admin.service.IUserService;
import com.immyc.blog.common.IdGen;
import com.immyc.blog.common.util.PwdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PwdUtil pwdUtil;

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
        return null;
    }
}
