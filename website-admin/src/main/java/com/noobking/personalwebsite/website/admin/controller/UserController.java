package com.noobking.personalwebsite.website.admin.controller;

import com.github.pagehelper.PageInfo;
import com.noobking.personalwebsite.common.constant.SystemConstant;
import com.noobking.personalwebsite.common.dto.ResponseResult;
import com.noobking.personalwebsite.domain.User;
import com.noobking.personalwebsite.domain.dto.LoginUserInfo;
import com.noobking.personalwebsite.domain.dto.UserMainInfo;
import com.noobking.personalwebsite.domain.dto.UserRights;
import com.noobking.personalwebsite.website.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 后台：用户控制
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户列表
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/user/list")
    public String userList(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        ResponseResult<PageInfo<UserMainInfo>> responseResult = userService.getUserMainInfo(pageNum, 10);
        if (responseResult.getCode() == HttpStatus.OK.value()) {
            PageInfo<UserMainInfo> pageInfo = responseResult.getData();
            model.addAttribute("pageInfo", pageInfo);
        } else {
            model.addAttribute("responseResult", responseResult);
        }
        return "user_management";
    }

    /**
     * 用户信息
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/user/info")
    public String userAllInfo(Long id, Model model) {
        ResponseResult<User> responseResult = userService.getUserInfoById(id);
        //获取成功
        if (responseResult.getCode() == HttpStatus.OK.value()) {
            User userAllInfo = responseResult.getData();
            model.addAttribute("userAllInfo", userAllInfo);
            return "_user_info";
        }
        //获取失败
        return "error/404";
    }

    @GetMapping("/user/edit")
    public String editUser(Long id, Model model) {
        ResponseResult<User> responseResult = userService.getUserInfoById(id);
        if (responseResult.getCode() == HttpStatus.OK.value()) {
            User userAllInfo = responseResult.getData();
            model.addAttribute("userAllInfo", userAllInfo);
            return "_user_edit";
        }
        //获取失败
        return "error/404";
    }

    /**
     * 编辑用户
     *
     * @param userRights
     * @return
     */
    @PostMapping("/user/edit")
    public String editUser(UserRights userRights, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        ResponseResult<Void> responseResult = new ResponseResult<Void>(HttpStatus.NOT_FOUND.value(), "没有找到", null);
        LoginUserInfo loginUser = (LoginUserInfo) request.getSession().getAttribute(SystemConstant.LOGIN_USER);
        if (loginUser.getId() == userRights.getId()) {
            responseResult.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseResult.setMessage("不能操作自己");
            redirectAttributes.addFlashAttribute("responseResult", responseResult);
            return "redirect:/user/list";
        }
        responseResult = userService.updateUserRights(userRights);
        if (responseResult.getCode() == HttpStatus.OK.value()) {
            redirectAttributes.addFlashAttribute("responseResult", responseResult);
            return "redirect:/user/list";
        }
        //获取失败
        return "error/404";
    }

    /**
     * 添加用户表单
     *
     * @return
     */
    @GetMapping("/user/add")
    public String addUser() {
        return "_user_add";
    }

    /**
     * 添加用户响应
     *
     * @param user
     * @return
     */
    @ResponseBody
    @PostMapping("/user/add")
    public ResponseResult<Void> addUser(User user) {
        //这里简易验证一下
        if (user.getUsername() == null || "".equals(user.getUsername())) {
            return new ResponseResult<Void>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "用户名错误");
        }
        if (user.getPassword() == null || "".equals(user.getPassword())) {
            return new ResponseResult<Void>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "密码错误");
        }
        if (user.getEmail() == null || "".equals(user.getEmail())) {
            return new ResponseResult<Void>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "邮箱错误");
        }
        if (user.getNote() == null || "".equals(user.getNote())) {
            return new ResponseResult<Void>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "简述错误");
        }
        ResponseResult<Void> responseResult = userService.addUser(user);
        return responseResult;
    }

    /**
     * 删除用户
     *
     * @param id
     * @param request
     * @param redirectAttributes
     * @return
     */
    @GetMapping("/user/delete")
    public String delUser(Long id, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        ResponseResult<Void> responseResult = new ResponseResult<Void>(HttpStatus.NOT_FOUND.value(), "没有找到", null);
        LoginUserInfo loginUser = (LoginUserInfo) request.getSession().getAttribute(SystemConstant.LOGIN_USER);
        if (loginUser.getId() == id) {
            responseResult.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseResult.setMessage("不能操作自己");
            redirectAttributes.addFlashAttribute("responseResult", responseResult);
            return "redirect:/user/list";
        }
        responseResult = userService.deleteUser(id);
        if (responseResult.getCode() == HttpStatus.OK.value()) {
            redirectAttributes.addFlashAttribute("responseResult", responseResult);
            return "redirect:/user/list";
        }
        //获取失败
        return "error/404";
    }
}
