package com.immyc.blog.admin.dao;

import com.immyc.blog.admin.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    int insert(User record);

    List<User> selectAll();
}