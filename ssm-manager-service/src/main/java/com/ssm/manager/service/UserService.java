package com.ssm.manager.service;

import com.ssm.manager.exception.UserException;
import com.ssm.manager.pojo.Permission;
import com.ssm.manager.pojo.Role;
import com.ssm.manager.pojo.UserLogin;

import java.util.List;

public interface UserService {

    void addUserLogin(String userName, String password);

    UserLogin login(String userName, String password) throws UserException;

    UserLogin getLoginInfo(long userId);

    UserLogin getLoginInfo(String userName);

    List<Role> findRole(long userId);

    List<Permission> findPermission(long userId);
}
