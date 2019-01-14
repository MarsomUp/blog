package com.immyc.blog.admin.dao;

import com.immyc.blog.admin.model.RolePermission;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolePermissionMapper {
    int insert(RolePermission record);

    List<RolePermission> selectAll();
}