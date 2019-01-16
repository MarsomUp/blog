package com.immyc.blog.admin.dao;

import com.immyc.blog.admin.model.RolePermission;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolePermissionMapper {
    int insert(RolePermission record);

    List<RolePermission> selectAll();

    void addRolePermPatch(List<RolePermission> list);

    void delRolePermPatch(List<Long> list);

    @Select("select * from sys_role_permission where permission_id = #{permissionId}")
    List<RolePermission> findByPermissionId(Long permissionId);
}