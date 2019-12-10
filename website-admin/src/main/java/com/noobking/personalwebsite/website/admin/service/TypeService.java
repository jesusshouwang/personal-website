package com.noobking.personalwebsite.website.admin.service;

import com.noobking.personalwebsite.common.dto.ResponseResult;
import com.noobking.personalwebsite.domain.Type;

import java.util.List;

public interface TypeService {

    /**
     * 获取全部类型
     *
     * @return
     */
    ResponseResult<List<Type>> getAllTypeInfo();
}
