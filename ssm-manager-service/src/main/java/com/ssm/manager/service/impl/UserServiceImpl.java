package com.ssm.manager.service.impl;

import com.ssm.manager.dao.UserDao;
import com.ssm.manager.pojo.User;
import com.ssm.manager.redisDao.RedisBaseDao;
import com.ssm.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisBaseDao redisBaseDao;

    public User get(int id) {
        User user = (User) redisBaseDao.get(id + "");
        if (user == null){
            user = userDao.select(id);
        }
        return user;
    }

    public void add(User user) {
        redisBaseDao.set(user.getId() + "",user,0);
    }


}
