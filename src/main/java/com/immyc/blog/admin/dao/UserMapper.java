package com.immyc.blog.admin.dao;

import com.immyc.blog.admin.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper {

    int insert(User record);

    List<User> selectAll(@Param("param") Map param);

    void updateUser(User user);

    @Select("select * from sys_user where id = #{id}")
    User getById(Long id);

    @Results({
            @Result(property = "userName", column = "user_name"),
            @Result(property = "loginAccount", column = "login_account"),
            @Result(property = "isDeleted", column = "is_deleted"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "createTime", column = "create_time")
    })
    @Select("select * from sys_user user where login_account = #{loginName}")
    User getByLoginName(String loginName);
}