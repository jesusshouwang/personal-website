package com.noobking.personalwebsite.website.admin.config;

import com.noobking.personalwebsite.website.admin.config.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web配置
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                //拦截User下的路径
                .addPathPatterns("/user/**")
                //拦截admin下的路径
                .addPathPatterns("/admin/**")
                //拦截article下路径
                .addPathPatterns("/article/**")
                //拦截website下路径
                .addPathPatterns("/website/**")
                //开放登陆接口
                .excludePathPatterns("/user/login");
    }

}
