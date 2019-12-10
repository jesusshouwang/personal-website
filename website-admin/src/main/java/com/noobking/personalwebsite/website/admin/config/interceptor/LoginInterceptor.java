package com.noobking.personalwebsite.website.admin.config.interceptor;

import com.noobking.personalwebsite.common.constant.SystemConstant;
import com.noobking.personalwebsite.domain.dto.LoginUserInfo;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登陆拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LoginUserInfo loginUser = (LoginUserInfo) request.getSession().getAttribute(SystemConstant.LOGIN_USER);
        if (loginUser == null){
            response.sendRedirect("/user/login");
            return false;
        }
        return true;
    }
}
