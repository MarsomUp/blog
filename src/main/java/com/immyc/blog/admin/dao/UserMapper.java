package com.immyc.blog.admin.dao;

import com.immyc.blog.admin.model.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    int insert(User record);

    List<User> selectAll();

    void updateUser(User user);

    @Select("select * from sys_user where id = #{id}")
    User getById(Long id);

    @Select("select * from sys_user where login_account = #{loginName}")
    User getByLoginName(String loginName);
}