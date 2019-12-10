package com.noobking.personalwebsite.website.admin.service.impl;

import com.noobking.personalwebsite.common.dto.ResponseResult;
import com.noobking.personalwebsite.domain.Type;
import com.noobking.personalwebsite.website.admin.mapper.TypeMapper;
import com.noobking.personalwebsite.website.admin.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    /**
     * 获取用户所有信息
     *
     * @return
     */
    @Override
    public ResponseResult<List<Type>> getAllTypeInfo() {
        List<Type> typeInfoList = typeMapper.getAllType();
        if (typeInfoList.isEmpty()) {
            return new ResponseResult<List<Type>>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "获取类型信息失败", null);
        }
        return new ResponseResult<List<Type>>(HttpStatus.OK.value(), "获取类型成功", typeInfoList);
    }
}
