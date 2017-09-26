package com.zy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;


/**
 * Created by Administrator on 2017/6/22.
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public String Login(){
        return "login/login";
    }

    @RequestMapping("/login")
    public String Login1(){
        return "login/login";
    }

    @RequestMapping("/index")
    public String Index(HttpServletRequest request){
        return "index/index";
    }

    @RequestMapping("/lock")
    public ModelAndView Lock(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login/lock");
        return modelAndView;
    }
}
