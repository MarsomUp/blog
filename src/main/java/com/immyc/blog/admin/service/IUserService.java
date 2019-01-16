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

    /**
     * 禁用人员
     * @param id
     */
    void disableUser(Long id);

    /**
     * 根据ID获取用户
     * @param id
     * @return
     */
    User getById(Long id);
}
