package com.ssm.manager.dao;

import com.ssm.manager.pojo.Permission;

import java.util.List;

public interface PermissionDao {

    Permission select(int authorizationId);

    List<Permission> selectPermissionByRoleId(int roleId);

    List<Permission> selectPermissionByUserId(long userId);
}
