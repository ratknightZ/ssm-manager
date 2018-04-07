package com.ssm.manager.dao;

import com.ssm.manager.pojo.UserLogin;

public interface UserLoginDao {

    UserLogin select(long userId);

    void insert(UserLogin userLogin);

    UserLogin selectByUserName(String userName);
}
