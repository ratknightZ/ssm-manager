package com.ssm.manager.controller;

import com.ssm.manager.pojo.User;
import com.ssm.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @GetMapping("/user/get")
    public String get(@RequestParam int id){
        return userService.get(id).toString();
    }

    @ResponseBody
    @GetMapping("/user/add")
    public String add(@RequestParam int id, @RequestParam String nick){
        User user = new User();
        user.setId(id);
        user.setNick(nick);
        userService.add(user);
        return "保存";
    }
}
