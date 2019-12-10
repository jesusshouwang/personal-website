package com.noobking.personalwebsite.website.admin.mapper;

import com.noobking.personalwebsite.domain.User;
import com.noobking.personalwebsite.domain.dto.UserMainInfo;
import com.noobking.personalwebsite.domain.dto.UserRights;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 数据访问层：用户
 */
@Repository
public interface UserMapper {

    /**
     * 根据用户id获取用户全部信息
     *
     * @param id
     * @return
     */
    User getUserById(Long id);

    /**
     * 根据用户名获取用户
     *
     * @param username
     * @return
     */
    User getByUsername(String username);

    /**
     * 获取全部用户的主要信息
     *
     * @return
     */
    List<UserMainInfo> getAllUserMainInfo(int pageNum, int pageSize);

    /**
     * 更新用户登陆时间
     */
    void updateLoginTime(User user);

    /**
     * 更新用户权限
     *
     * @param userRights
     */
    void updateUserRights(UserRights userRights);

    /**
     * 插入新用户
     *
     * @param user
     */
    void insertUser(User user);

    /**
     * 删除用户
     *
     * @param id
     */
    void deleteUser(Long id);
}
