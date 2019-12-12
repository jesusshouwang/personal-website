package com.noobking.personalwebsite.website.admin.controller;

import com.noobking.personalwebsite.common.dto.ResponseResult;
import com.noobking.personalwebsite.domain.Website;
import com.noobking.personalwebsite.website.admin.service.WebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class WebsiteController {

    @Autowired
    private WebsiteService websiteService;

    @GetMapping(value = "/website/list")
    public String websiteList(Model model) {
        ResponseResult<List<Website>> responseResult = websiteService.getAllWebsite();
        //获取成功
        if (responseResult.getCode() == HttpStatus.OK.value()) {
            List<Website> websiteList = responseResult.getData();
            model.addAttribute("websiteList", websiteList);
        } else {
            model.addAttribute("responseResult", responseResult);
        }
        return "website_management";
    }

    @GetMapping(value = "/website/add")
    public String addWebsite() {
        return "_website_add";
    }

    @ResponseBody
    @PostMapping(value = "/website/add")
    public ResponseResult<Void> addWebsite(Website website) {
        ResponseResult<Void> responseResult = websiteService.saveWebsite(website);
        return responseResult;
    }

    @GetMapping(value = "/website/edit")
    public String editWebsite(Long id, Model model, RedirectAttributes redirectAttributes) {
        ResponseResult<Website> responseResult = websiteService.getWebsiteById(id);
        if (responseResult.getCode() == HttpStatus.OK.value()) {
            Website websiteInfo = responseResult.getData();
            model.addAttribute("websiteInfo", websiteInfo);
            return "_website_edit";
        }
        redirectAttributes.addFlashAttribute("responseResult", responseResult);
        return "redirect:/website/list";
    }

    @ResponseBody
    @PostMapping(value = "/website/edit")
    public ResponseResult<Void> editWebsite(Website website) {
        ResponseResult<Void> responseResult = websiteService.saveWebsite(website);
        return responseResult;
    }

    @ResponseBody
    @PostMapping(value = "/website/del")
    public ResponseResult<Void> delWebsite(Long id, RedirectAttributes redirectAttributes) {
        ResponseResult<Void> responseResult = websiteService.deleteWebsite(id);
        return responseResult;
    }
}
