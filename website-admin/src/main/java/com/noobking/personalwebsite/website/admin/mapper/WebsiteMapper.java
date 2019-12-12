package com.noobking.personalwebsite.website.admin.mapper;

import com.noobking.personalwebsite.domain.Website;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 数据访问层-推荐站点
 */
@Repository
public interface WebsiteMapper {

    /**
     * 根据id获取website全部信息
     *
     * @param id
     * @return
     */
    Website getWebsiteById(Long id);

    /**
     * 插入站点信息
     */
    void insertWebsite(Website website);

    /**
     * 更新站点信息
     *
     * @param website
     */
    void updateWebsite(Website website);

    /**
     * 获取所有站点全部信息
     *
     * @return
     */
    List<Website> getAllWebsite();

    /**
     * 根据ID删除website
     *
     * @param id
     */
    void deleteWebsite(Long id);
}
