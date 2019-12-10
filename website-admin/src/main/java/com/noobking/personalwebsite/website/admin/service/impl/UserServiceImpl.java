package com.noobking.personalwebsite.website.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.noobking.personalwebsite.common.dto.ResponseResult;
import com.noobking.personalwebsite.domain.User;
import com.noobking.personalwebsite.domain.dto.UserMainInfo;
import com.noobking.personalwebsite.domain.dto.UserRights;
import com.noobking.personalwebsite.website.admin.mapper.UserMapper;
import com.noobking.personalwebsite.website.admin.service.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 业务逻辑层：用户服务
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据用户ID获取用户全部信息
     *
     * @param id
     * @return
     */
    @Override
    public ResponseResult<User> getUserInfoById(Long id) {
        User userAllInfo = userMapper.getUserById(id);
        if (userAllInfo == null) {
            return new ResponseResult<User>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "未查找到此用户", null);
        }
        return new ResponseResult<User>(HttpStatus.OK.value(), "获取用户信息成功", userAllInfo);
    }

    /**
     * 获取用户主要信息
     *
     * @return
     */
    @Override
    public ResponseResult<PageInfo<UserMainInfo>> getUserMainInfo(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<UserMainInfo> pageInfo = new PageInfo<UserMainInfo>(userMapper.getAllUserMainInfo(pageNum, pageSize));
        return new ResponseResult<PageInfo<UserMainInfo>>(HttpStatus.OK.value(), "获取用户成功", pageInfo);
    }

    @Override
    public ResponseResult<Void> updateUserRights(UserRights userRights) {
        userMapper.updateUserRights(userRights);
        return new ResponseResult<Void>(HttpStatus.OK.value(), "修改成功", null);
    }

    /**
     * 添加用户
     *
     * @param user
     */
    @Override
    public ResponseResult<Void> addUser(User user) {
        User existUser = userMapper.getByUsername(user.getUsername());
        //如果该用户名已经存在
        if (existUser != null) {
            return new ResponseResult<Void>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "该用户名已经存在", null);
        }
        //如果用户名不存在就可以添加
        else {
            user.setCreateTime(new Date());
            user.setLastLoginTime(null);
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            userMapper.insertUser(user);
            return new ResponseResult<Void>(HttpStatus.OK.value(), "添加用户成功", null);
        }
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @Override
    public ResponseResult<Void> deleteUser(Long id) {
        User exsitUser = userMapper.getUserById(id);
        if (exsitUser == null) {
            return new ResponseResult<Void>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "没有找到用户", null);
        }
        userMapper.deleteUser(id);
        return new ResponseResult<Void>(HttpStatus.OK.value(), "删除用户成功", null);
    }
}
