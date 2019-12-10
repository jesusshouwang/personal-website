package com.noobking.personalwebsite.website.admin.controller;

import com.noobking.personalwebsite.common.constant.SystemConstant;
import com.noobking.personalwebsite.common.dto.ResponseResult;
import com.noobking.personalwebsite.domain.dto.LoginUserInfo;
import com.noobking.personalwebsite.website.admin.dto.LoginParam;
import com.noobking.personalwebsite.website.admin.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 登陆控制
 */
@Controller
@RequestMapping(value = "/user")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @PostMapping(value = "/login")
    public String login(LoginParam loginParam, Model model, HttpServletRequest request) {
        ResponseResult<LoginUserInfo> loginInfo = loginService.login(loginParam);
        //如果登陆成功
        if (loginInfo.getCode() == HttpStatus.OK.value()) {
            LoginUserInfo data = loginInfo.getData();
            request.getSession().setAttribute(SystemConstant.LOGIN_USER, data);
            return "redirect:/admin/main";
        }
        model.addAttribute("ResponseResult", loginInfo);
        model.addAttribute("loginParam", loginParam);
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/user/login";
    }
}
