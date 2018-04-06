package com.ssm.manager.service;

import com.ssm.manager.pojo.User;

public interface UserService {

    User get(int id);

    void add(User user);
}
