package com.noobking.personalwebsite.website.admin.service;

import com.noobking.personalwebsite.common.dto.ResponseResult;
import com.noobking.personalwebsite.domain.dto.LoginUserInfo;
import com.noobking.personalwebsite.website.admin.dto.LoginParam;

/**
 * 登陆服务接口
 */
public interface LoginService {
    /**
     * 登陆
     *
     * @param loginParam
     * @return
     */
    ResponseResult<LoginUserInfo> login(LoginParam loginParam);
}
