package com.ssm.manager.controller;

import com.ssm.manager.exception.UserException;
import com.ssm.manager.pojo.UserLogin;
import com.ssm.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserLoginController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @PostMapping("/register")
    public String register(@RequestParam String userName,
                           @RequestParam String password){
        if (userName.length() == 0){

        }
        if (password.length() == 0){

        }
        userService.addUserLogin(userName,password);
        return "";
    }

    @ResponseBody
    @PostMapping("/login")
    public String login(@RequestParam String userName,
                        @RequestParam String password){

        try {
            UserLogin userLogin = userService.login(userName,password);
        } catch (UserException e) {
            e.printStackTrace();
        }
        return userName;
    }

    @ResponseBody
    @GetMapping("/loginout")
    public String loginout(){
        userService.loginout();
        return "true";
    }
}
