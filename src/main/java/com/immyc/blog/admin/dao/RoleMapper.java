package com.immyc.blog.admin.dao;

import com.immyc.blog.admin.model.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {
    int insert(Role record);

    List<Role> selectAll();
}