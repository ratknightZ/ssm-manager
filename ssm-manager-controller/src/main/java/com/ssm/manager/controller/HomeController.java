package com.ssm.manager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    private Logger logger = LoggerFactory.getLogger(HomeController.class);

    @GetMapping(value = "/")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("template/index");
        mv.addObject("a","hello beetl!");
        return mv;
    }
}
