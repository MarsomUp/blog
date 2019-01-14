package com.immyc.blog.admin.service;

import com.immyc.blog.admin.model.User;

public interface IUserService {

    /**
     * 新增人员
     * @param user
     */
    void addUser(User user);

    /**
     * 登陆验证
     * @param loginName
     * @param pwd
     * @return
     */
    boolean login(String loginName, String pwd);

    /**
     * 根据登陆名获取用户信息
     * @param loginName
     * @return
     */
    User getByLoginName(String loginName);
}
