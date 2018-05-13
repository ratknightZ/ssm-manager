package com.ssm.manager.service.impl;

import com.ssm.manager.dao.PermissionDao;
import com.ssm.manager.dao.RoleDao;
import com.ssm.manager.dao.UserLoginDao;
import com.ssm.manager.enums.RoleEnum;
import com.ssm.manager.exception.UserException;
import com.ssm.manager.pojo.Permission;
import com.ssm.manager.pojo.Role;
import com.ssm.manager.pojo.UserLogin;
import com.ssm.manager.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserLoginDao userLoginDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PermissionDao permissionDao;

    private String algorithmName = "md5";

    private final int hashIterations = 2;

    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    @CachePut(value = "userLogin",key = "#result.userId")
    public UserLogin addUserLogin(String userName, String password) {
        UserLogin userLogin = new UserLogin();
        userLogin.setSalt(randomNumberGenerator.nextBytes().toHex());
        String userPassword = new SimpleHash(algorithmName,password, ByteSource.Util.bytes(userLogin.getSalt()),hashIterations).toHex();
        userLogin.setUserName(userName);
        userLogin.setUserPassword(userPassword);
        userLoginDao.insert(userLogin);
        roleDao.insertUserRoleR(userLogin.getUserId(), RoleEnum.USER.getValue());
        return userLogin;
    }

    @CachePut(value = "userLogin",key = "#result.userId")
    public UserLogin login(String userName, String password) throws UserException {
        UserLogin userLogin = userLoginDao.selectByUserName(userName);
        if (userLogin == null){
            throw new UserException("用户不存在");
        }
        if (!userLogin.getUserPassword().equals(new SimpleHash(algorithmName,password, ByteSource.Util.bytes(userLogin.getSalt()),hashIterations).toHex())){
            throw new UserException("密码错误");
        }
        UsernamePasswordToken token = new UsernamePasswordToken(userLogin.getUserName(),userLogin.getUserPassword());
        token.setRememberMe(true);
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.login(token);
        return userLogin;
    }

    public void loginout() {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
    }

    @Cacheable(value = "userLogin",key = "#userId")
    public UserLogin getLoginInfo(long userId) {
        return userLoginDao.select(userId);
    }

    public UserLogin getLoginInfo(String userName) {
        return userLoginDao.selectByUserName(userName);
    }

    public List<Role> findRole(long userId) {
        return roleDao.selectRoleByUserId(userId);
    }

    public List<Permission> findPermission(long userId) {
        return permissionDao.selectPermissionByUserId(userId);
    }


}
