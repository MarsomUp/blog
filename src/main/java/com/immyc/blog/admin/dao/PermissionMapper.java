package com.immyc.blog.admin.dao;

import com.immyc.blog.admin.model.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionMapper {
    int insert(Permission record);

    List<Permission> selectAll();
}