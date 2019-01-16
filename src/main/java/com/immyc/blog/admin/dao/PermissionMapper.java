package com.immyc.blog.admin.dao;

import com.immyc.blog.admin.model.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionMapper {
    int insert(Permission record);

    List<Permission> selectAll();

    @Delete("delete from sys_permission where id = #{id}")
    void deletePermission(Long id);

    @Select("select * from sys_permission where id = #{id}")
    Permission getById(Long id);

    void updatePermission(Permission permission);
}