package com.noobking.personalwebsite.website.admin.mapper;

import com.noobking.personalwebsite.domain.Article;
import com.noobking.personalwebsite.domain.dto.ArticleMainInfo;
import com.noobking.personalwebsite.domain.dto.ArticleStatusInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 数据访问层-文章
 */
@Repository
public interface ArticleMapper {

    /**
     * 根据id查找文章全部信息
     *
     * @param id
     * @return
     */
    Article getArticleById(Long id);

    /**
     * 获取所有文章的主要信息
     *
     * @return
     */
    List<ArticleMainInfo> getAllArticleMainInfo(int pageNum, int pageSize);

    /**
     * 添加新文章
     *
     * @param article
     */
    void insertArticle(Article article);

    /**
     * 更新文章
     *
     * @param article
     */
    void updateArticle(Article article);

    /**
     * 根据id获取文章状态信息
     *
     * @param id
     * @return
     */
    ArticleStatusInfo getArticleStatusInfoById(Long id);

    /**
     * 更新文章状态
     *
     * @param articleStatusInfo
     */
    void updateArticleStatusInfo(ArticleStatusInfo articleStatusInfo);

    /**
     * 删除文章
     * @param id
     */
    void deleteArticleById(Long id);
}
