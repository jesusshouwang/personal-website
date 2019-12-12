package com.noobking.personalwebsite.website.admin.service.impl;

import com.noobking.personalwebsite.common.dto.ResponseResult;
import com.noobking.personalwebsite.domain.Website;
import com.noobking.personalwebsite.website.admin.mapper.WebsiteMapper;
import com.noobking.personalwebsite.website.admin.service.WebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebsiteServiceImpl implements WebsiteService {

    @Autowired
    private WebsiteMapper websiteMapper;

    /**
     * 根据ID获取website全部信息
     *
     * @param id
     * @return
     */
    @Override
    public ResponseResult<Website> getWebsiteById(Long id) {
        Website website = websiteMapper.getWebsiteById(id);
        if (website == null) {
            return new ResponseResult<Website>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "没有找到该站点信息", null);
        }
        return new ResponseResult<Website>(HttpStatus.OK.value(), "获取成功", website);
    }

    /**
     * 插入新站点信息
     *
     * @param website
     * @return
     */
    @Override
    public ResponseResult<Void> saveWebsite(Website website) {
        //插入新站点
        if (website.getId() == null) {
            websiteMapper.insertWebsite(website);
            return new ResponseResult<Void>(HttpStatus.OK.value(), "增加推荐站点成功", null);
        }
        //保存修改站点数据
        else {
            websiteMapper.updateWebsite(website);
            return new ResponseResult<Void>(HttpStatus.OK.value(), "保存推荐站点成功", null);
        }

    }

    /**
     * 获取所有站点信息
     *
     * @return
     */
    @Override
    public ResponseResult<List<Website>> getAllWebsite() {
        List<Website> websiteList = websiteMapper.getAllWebsite();
        if (websiteList.isEmpty()) {
            return new ResponseResult<List<Website>>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "无推荐站点", null);
        }
        return new ResponseResult<List<Website>>(HttpStatus.OK.value(), "获取推荐站点成功", websiteList);
    }

    /**
     * 根据ID删除website
     *
     * @param id
     * @return
     */
    @Override
    public ResponseResult<Void> deleteWebsite(Long id) {
        websiteMapper.deleteWebsite(id);
        return new ResponseResult<Void>(HttpStatus.OK.value(), "删除站点成功", null);
    }
}
