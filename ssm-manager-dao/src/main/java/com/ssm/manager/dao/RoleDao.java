package com.ssm.manager.dao;

import com.ssm.manager.pojo.Role;

import java.util.List;

public interface RoleDao {

    Role select(int roleId);

    List<Role> selectRoleByUserId(long userId);
}
