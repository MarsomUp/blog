package com.immyc.blog.admin.dao;

import com.immyc.blog.admin.model.UserRole;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleMapper {
    int insert(UserRole record);

    List<UserRole> selectAll();
}