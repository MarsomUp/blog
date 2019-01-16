package com.immyc.blog.admin.dao;

import com.immyc.blog.admin.model.Role;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {

    int insert(Role record);

    List<Role> selectAll();

    void updateRole(Role role);

    void deleteRole(Long id);

    Integer maxRoleSort();

    @Select("select * from sys_role where id = #{id}")
    Role getById(Long id);
}