package com.immyc.blog.admin.dao;

import com.immyc.blog.admin.model.UserRole;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleMapper {
    int insert(UserRole record);

    List<UserRole> selectAll();

    void addUserRolePatch(List<UserRole> userRoles);

    void deleteUserRolePatch(List<Long> ids);

    @Select("select * from sys_user_role where role_id = #{roleId}")
    List<UserRole> findUserRoleByRoleId(Long roleId);
}