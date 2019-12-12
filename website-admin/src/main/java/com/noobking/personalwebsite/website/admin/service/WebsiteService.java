package com.noobking.personalwebsite.website.admin.service;

import com.noobking.personalwebsite.common.dto.ResponseResult;
import com.noobking.personalwebsite.domain.Website;

import java.util.List;

public interface WebsiteService {

    /**
     * 根据ID获取website全部信息
     *
     * @param id
     * @return
     */
    ResponseResult<Website> getWebsiteById(Long id);

    /**
     * 保存站点
     *
     * @param website
     * @return
     */
    ResponseResult<Void> saveWebsite(Website website);


    /**
     * 获取所有站点信息
     *
     * @return
     */
    ResponseResult<List<Website>> getAllWebsite();

    /**
     * 根据id删除website
     * @param id
     * @return
     */
    ResponseResult<Void> deleteWebsite(Long id);
}
