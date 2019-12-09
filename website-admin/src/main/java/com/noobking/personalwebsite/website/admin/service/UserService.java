package com.noobking.personalwebsite.website.admin.service;

import com.noobking.personalwebsite.common.dto.ResponseResult;
import com.noobking.personalwebsite.domain.User;
import com.noobking.personalwebsite.domain.dto.UserMainInfo;
import com.noobking.personalwebsite.domain.dto.UserRights;

import java.util.List;

/**
 * 用户服务接口
 */
public interface UserService {

    /**
     * 根据用户ID查找用户全部信息
     * @param id
     * @return
     */
    ResponseResult<User> getUserInfoById(Long id);

    /**
     * 获取用户的主要信息
     *
     * @return
     */
    ResponseResult<List<UserMainInfo>> getUserMainInfo();

    /**
     * 更新用户权限
     *
     * @return
     */
    ResponseResult<Void> updateUserRights(UserRights userRights);

    /**
     * 添加新用户
     * @param user
     */
    ResponseResult<Void> addUser(User user);

    /**
     * 删除用户
     * @param id
     */
    ResponseResult<Void> deleteUser(Long id);
}
