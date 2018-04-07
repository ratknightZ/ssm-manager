package com.ssm.manager.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    private Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(){
        return "index";
    }
}
