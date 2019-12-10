package com.noobking.personalwebsite.website.admin.controller;

import com.github.pagehelper.PageInfo;
import com.noobking.personalwebsite.common.dto.ResponseResult;
import com.noobking.personalwebsite.domain.Article;
import com.noobking.personalwebsite.domain.Type;
import com.noobking.personalwebsite.domain.dto.ArticleMainInfo;
import com.noobking.personalwebsite.domain.dto.ArticleStatusInfo;
import com.noobking.personalwebsite.domain.dto.LoginUserInfo;
import com.noobking.personalwebsite.website.admin.service.ArticleService;
import com.noobking.personalwebsite.website.admin.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ArticleController {

    //注入文章服务
    @Autowired
    private ArticleService articleService;

    //注入文章类型服务
    @Autowired
    private TypeService typeService;

    @GetMapping(value = "/article/list")
    public String articleList(Model model,@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        ResponseResult<PageInfo<ArticleMainInfo>> responseResult = articleService.getAllArticleMainInfo(pageNum,10);
        //如果获取成功
        if (responseResult.getCode() == HttpStatus.OK.value()) {
            PageInfo<ArticleMainInfo> pageinfoAritcle = responseResult.getData();
            model.addAttribute("pageinfoAritcle", pageinfoAritcle);
        } else {
            model.addAttribute("responseResult", responseResult);
        }
        return "article_management";
    }

    @GetMapping("/article/add")
    public String addArticle(Long id, Model model) {
        //获取文章类型
        ResponseResult<List<Type>> responseResultType = typeService.getAllTypeInfo();
        List<Type> allTypeInfoList = responseResultType.getData();
        model.addAttribute("allTypeInfoList", allTypeInfoList);
        //编辑文章
        if (id != null) {
            ResponseResult<Article> responseResultArticle = articleService.getArticleById(id);
            Article articleAllInfo = responseResultArticle.getData();
            model.addAttribute("articleAllInfo", articleAllInfo);
        }
        return "article_add";
    }

    @PostMapping("/article/add")
    public String addArticle(Article article, RedirectAttributes redirectAttributes) {
        ResponseResult<Void> responseResult = articleService.saveArticle(article);
        if (responseResult.getCode() == HttpStatus.OK.value()) {
            redirectAttributes.addFlashAttribute("responseResult", responseResult);
            return "redirect:/article/list";
        }
        return "article_add";
    }

    @ResponseBody
    @GetMapping("/article/del/{id}")
    public ResponseResult<Void> delArticle(@PathVariable Long id, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        ResponseResult<Void> responseResult = new ResponseResult<Void>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "没有权限删除", null);
        LoginUserInfo loginUser = (LoginUserInfo) request.getSession().getAttribute("loginUser");
        if ("admin".equals(loginUser.getUsername())) {
            responseResult = articleService.deleteArticleById(id);
        }
        return responseResult;
    }

    @GetMapping("/article/status")
    public String editStatus(Long id, Model model) {
        ResponseResult<ArticleStatusInfo> responseResult = articleService.getArticleStatusInfoById(id);
        //获取文章状态成功
        if (responseResult.getCode() == HttpStatus.OK.value()) {
            ArticleStatusInfo articleStatusInfo = responseResult.getData();
            model.addAttribute("articleStatusInfo", articleStatusInfo);
            return "_article_edit_status";
        }
        return null;
    }

    @ResponseBody
    @PostMapping("/article/status")
    public ResponseResult<ArticleStatusInfo> editStatus(ArticleStatusInfo articleStatusInfo, Model model) {
        ResponseResult<ArticleStatusInfo> responseResult = articleService.updateArticleStatusInfo(articleStatusInfo);
        return responseResult;
    }
}
