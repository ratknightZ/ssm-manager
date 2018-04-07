package com.ssm.manager.shiro;

import com.ssm.manager.pojo.Permission;
import com.ssm.manager.pojo.Role;
import com.ssm.manager.pojo.UserLogin;
import com.ssm.manager.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 提供用户信息返回权限信息
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName = (String) principalCollection.getPrimaryPrincipal();
        UserLogin userLogin = userService.getLoginInfo(userName);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        List<Role> roleList = userService.findRole(userLogin.getUserId());
        Set<String> roleNameSet = new HashSet<String>();
        for (Role role : roleList){
            roleNameSet.add(role.getRoleName());
        }
        authorizationInfo.setRoles(roleNameSet);
        List<Permission> permissionList = userService.findPermission(userLogin.getUserId());
        Set<String> permissionNameSet = new HashSet<String>();
        for (Permission permission : permissionList){
            permissionNameSet.add(permission.getPermissionName());
        }
        authorizationInfo.setStringPermissions(permissionNameSet);
        return authorizationInfo;
    }

    /**
     * 提供账户信息返回认证信息
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName = (String) authenticationToken.getPrincipal();
        UserLogin userLogin = userService.getLoginInfo(userName);
        if (userLogin == null){
            throw new UnknownAccountException();
        }
        if (userLogin.getIsLock() == 1){
            throw new LockedAccountException();
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userLogin.getUserName(),userLogin.getUserPassword(), ByteSource.Util.bytes(userLogin.getSalt()),getName());
        return authenticationInfo;
    }
}
