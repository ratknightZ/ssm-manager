package com.ssm.manager.controller;

import com.ssm.commons.exception.vo.AppResultVO;
import com.ssm.manager.pojo.UserLogin;
import com.ssm.manager.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("/user/get_login_user")
    public AppResultVO getLoginUser(){
        Subject currentUser = SecurityUtils.getSubject();
        String userName = (String) currentUser.getPrincipal();
        UserLogin userLogin = userService.getLoginInfo(userName);
        AppResultVO resultVO = new AppResultVO();
        resultVO.setIsSuccess(1);
        resultVO.setData(userLogin);
        return resultVO;
    }
}
