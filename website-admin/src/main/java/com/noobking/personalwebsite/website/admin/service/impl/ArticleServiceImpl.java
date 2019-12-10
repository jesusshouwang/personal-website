package com.noobking.personalwebsite.website.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.noobking.personalwebsite.common.dto.ResponseResult;
import com.noobking.personalwebsite.domain.Article;
import com.noobking.personalwebsite.domain.dto.ArticleMainInfo;
import com.noobking.personalwebsite.domain.dto.ArticleStatusInfo;
import com.noobking.personalwebsite.website.admin.mapper.ArticleMapper;
import com.noobking.personalwebsite.website.admin.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public ResponseResult<Article> getArticleById(Long id) {
        Article article = articleMapper.getArticleById(id);
        if (article != null) {
            return new ResponseResult<Article>(HttpStatus.OK.value(), "查询文章成功", article);
        }
        return new ResponseResult<Article>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "未查询到文章", null);
    }

    /**
     * 获得所有文章主要信息
     *
     * @return
     */
    @Override
    public ResponseResult<PageInfo<ArticleMainInfo>> getAllArticleMainInfo(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<ArticleMainInfo> pageInfo = new PageInfo<ArticleMainInfo>(articleMapper.getAllArticleMainInfo(pageNum, pageSize));
        return new ResponseResult<PageInfo<ArticleMainInfo>>(HttpStatus.OK.value(), "获取文章成功", pageInfo);
    }

    /**
     * 添加文章
     *
     * @param article
     * @return
     */
    @Override
    public ResponseResult<Void> saveArticle(Article article) {
        article.setUpdateTime(new Date());
        //添加新文章
        if (article.getId() == null || article.getId() == 0) {
            article.setCreateTime(new Date());
            articleMapper.insertArticle(article);
        }
        //编辑旧文章
        else {
            articleMapper.updateArticle(article);
        }

        if (article.isPublished()) {
            return new ResponseResult<Void>(HttpStatus.OK.value(), "发布文章成功", null);
        } else {
            return new ResponseResult<Void>(HttpStatus.OK.value(), "保存文章成功", null);
        }
    }

    /**
     * 根据id获取文章状态
     *
     * @param id
     * @return
     */
    @Override
    public ResponseResult<ArticleStatusInfo> getArticleStatusInfoById(Long id) {
        ArticleStatusInfo articleStatusInfo = articleMapper.getArticleStatusInfoById(id);
        if (articleStatusInfo != null) {
            return new ResponseResult<ArticleStatusInfo>(HttpStatus.OK.value(), "获取文章状态成功", articleStatusInfo);
        }
        return new ResponseResult<ArticleStatusInfo>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "获取文章状态失败", null);

    }

    /**
     * 更新文章状态
     *
     * @param articleStatusInfo
     * @return
     */
    @Override
    public ResponseResult<ArticleStatusInfo> updateArticleStatusInfo(ArticleStatusInfo articleStatusInfo) {
        articleMapper.updateArticleStatusInfo(articleStatusInfo);
        return new ResponseResult<ArticleStatusInfo>(HttpStatus.OK.value(), "更新文章状态成功", null);
    }

    /**
     * 删除文章
     *
     * @param id
     * @return
     */
    @Override
    public ResponseResult<Void> deleteArticleById(Long id) {
        articleMapper.deleteArticleById(id);
        return new ResponseResult<Void>(HttpStatus.OK.value(), "删除文章成功", null);
    }
}
