package com.noobking.personalwebsite.website.admin.service;

import com.github.pagehelper.PageInfo;
import com.noobking.personalwebsite.common.dto.ResponseResult;
import com.noobking.personalwebsite.domain.Article;
import com.noobking.personalwebsite.domain.dto.ArticleMainInfo;
import com.noobking.personalwebsite.domain.dto.ArticleStatusInfo;

import java.util.List;

public interface ArticleService {

    /**
     * 根据id查找文章全部信息
     * @param id
     * @return
     */
    ResponseResult<Article> getArticleById(Long id);

    /**
     * 获得所有文章主要信息
     *
     * @return
     */
    ResponseResult<PageInfo<ArticleMainInfo>> getAllArticleMainInfo(int pageNum, int pageSize);

    /**
     * 后台添加文章
     *
     * @param article
     * @return
     */
    ResponseResult<Void> saveArticle(Article article);

    /**
     * 根据id获取文章状态
     * @param id
     * @return
     */
    ResponseResult<ArticleStatusInfo> getArticleStatusInfoById(Long id);

    /**
     * 更新文章状态
     * @param articleStatusInfo
     * @return
     */
    ResponseResult<ArticleStatusInfo> updateArticleStatusInfo(ArticleStatusInfo articleStatusInfo);

    /**
     * 删除文章
     * @param id
     * @return
     */
    ResponseResult<Void> deleteArticleById(Long id);
}
