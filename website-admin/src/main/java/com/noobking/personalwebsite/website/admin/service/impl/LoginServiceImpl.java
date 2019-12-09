package com.noobking.personalwebsite.website.admin.service.impl;

import com.noobking.personalwebsite.common.dto.ResponseResult;
import com.noobking.personalwebsite.domain.User;
import com.noobking.personalwebsite.domain.dto.LoginUserInfo;
import com.noobking.personalwebsite.website.admin.dto.LoginParam;
import com.noobking.personalwebsite.website.admin.mapper.UserMapper;
import com.noobking.personalwebsite.website.admin.service.LoginService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 业务逻辑层：登陆服务
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;


    /**
     * 实现逻辑：根据用户名查找用户
     *
     * @param loginParam
     * @return
     */
    @Override
    public ResponseResult<LoginUserInfo> login(LoginParam loginParam) {
        User user = userMapper.getByUsername(loginParam.getUsername());
        //如果用户存在
        if (user != null) {
            //验证密码是否正确，密码使用了BCrypt加密
            if (BCrypt.checkpw(loginParam.getPassword(), user.getPassword())) {
                //如果有权限
                if (user.isRights() && user.isStatus()){
                    LoginUserInfo loginUser = new LoginUserInfo();
                    loginUser.setId(user.getId());
                    loginUser.setAvatar(user.getAvatar());
                    loginUser.setUsername(user.getUsername());
                    loginUser.setNote(user.getNote());
                    loginUser.setLastLoginTime(user.getLastLoginTime());
                    //刷新上次登陆时间
                    user.setLastLoginTime(new Date());
                    userMapper.updateLoginTime(user);
                    return new ResponseResult<LoginUserInfo>(HttpStatus.OK.value(), "登陆成功", loginUser);
                }
                else {
                    return new ResponseResult<LoginUserInfo>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "该账号无登陆权限或被禁用", null);
                }
            }
            return new ResponseResult<LoginUserInfo>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "密码错误", null);
        }
        return new ResponseResult<LoginUserInfo>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "无此账号", null);
    }
}
