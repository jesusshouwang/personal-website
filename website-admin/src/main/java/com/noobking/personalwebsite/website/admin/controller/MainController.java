package com.noobking.personalwebsite.website.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 后台主页面跳转
 */
@Controller
@RequestMapping(value = "/admin")
public class MainController {

    @GetMapping("/main")
    public String main(){
        return "main";
    }
}
