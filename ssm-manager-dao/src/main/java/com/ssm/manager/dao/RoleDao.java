package com.ssm.manager.dao;

import com.ssm.manager.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleDao {

    Role select(int roleId);

    List<Role> selectRoleByUserId(long userId);

    void insertUserRoleR(@Param("userId") long userId, @Param("roleId") int roleId);
}
