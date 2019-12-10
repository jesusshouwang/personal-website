package com.noobking.personalwebsite.website.admin.mapper;

import com.noobking.personalwebsite.domain.Type;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeMapper {

    /**
     * 获取所有的类型
     *
     * @return
     */
    List<Type> getAllType();
}
