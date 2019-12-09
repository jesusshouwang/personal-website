package com.noobking.personalwebsite.website.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 程序入口
 */
@SpringBootApplication
@MapperScan("com.noobking.personalwebsite.website.admin.mapper")
public class WebsiteAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebsiteAdminApplication.class, args);
    }
}
