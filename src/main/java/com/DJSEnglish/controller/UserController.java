package com.DJSEnglish.controller;

import com.DJSEnglish.common.Const;
import com.DJSEnglish.common.ServerResponse;
import com.DJSEnglish.dao.UserMapper;
import com.DJSEnglish.pojo.User;
import com.DJSEnglish.service.IFileService;
import com.DJSEnglish.service.IUserService;
import com.DJSEnglish.util.JWTUtil;
import com.DJSEnglish.util.PhoneUtil;
import com.DJSEnglish.util.PropertiesUtil;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.Map;

@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IFileService iFileService;

    @RequestMapping(value = "test.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse test(String test, HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String nextElement = headerNames.nextElement();
            System.out.println(nextElement + ":" + request.getHeader(nextElement));
        }
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String nextElement = parameterNames.nextElement();
            System.out.println(nextElement + ":" + request.getParameter(nextElement));
        }
        if (StringUtils.isNotBlank(test)) {
            return ServerResponse.createBySuccessMsg("参数" + test);
        }
        return ServerResponse.createByErrorMsg("失败");
    }


    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse login(String phoneNumber, String password) {
        ServerResponse serverResponse;
        try {
            serverResponse = iUserService.Login(phoneNumber, password);
        } catch (Exception e) {
            return ServerResponse.createByErrorMsg("登录失败");
        }
        return serverResponse;
    }


    @RequestMapping(value = "need_login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse needLogin() {
        return ServerResponse.createByErrorMsg("用户未登录");
    }

    @RequestMapping(value = "upload.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse upload(@RequestParam(value = "upload_file", required = false) MultipartFile file, HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute(Const.ID);
        String path = request.getSession().getServletContext().getRealPath("upload");
        String targetFileName = iFileService.upload(file, path);
        String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + targetFileName;
        if (targetFileName != null) {
            User newUser = new User();
            newUser.setImg(targetFileName);
            newUser.setId(userId);
            userMapper.updateByPrimaryKeySelective(newUser);
        }
        Map fileMap = Maps.newHashMap();
        fileMap.put("uri", targetFileName);
        fileMap.put("url", url);
        return ServerResponse.createBySuccess(fileMap);
    }


    @RequestMapping(value = "forget_reset_password.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse forgetResetPassword(String msgCode, String phoneNumber, String password) {

        return iUserService.forgetResetPassword(msgCode, phoneNumber, password);
    }

    @RequestMapping(value = "get_msgcode.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getMsgcode(String phoneNumber) {
        if (PhoneUtil.getVerificationCode(phoneNumber) != null) {
            return ServerResponse.createBySuccessMsg("发送成功");
        }
        return ServerResponse.createByErrorMsg("发送失败");
    }

    @RequestMapping(value = "login_reset_password.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse loginResetPassword(HttpServletRequest request, String password) {
        ServerResponse serverResponse;
        Integer userId = (Integer) request.getAttribute(Const.ID);
        if ((serverResponse = iUserService.loginResetPassword(userId, password)).isSuccess())
        {
            return serverResponse;
        }
        else{
            return ServerResponse.createByErrorMsg("重置失败");
        }
    }


    @RequestMapping(value = "register.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse register(User user, String msgCode) {

        return iUserService.Register(user, msgCode);
    }

    @RequestMapping(value = "check_msg.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse checkMsg(String phone, String msgCode) {
        if (!PhoneUtil.judgeCodeIsTrue(msgCode, phone)) {
            return ServerResponse.createByErrorMsg("验证码不正确");
        }
        return ServerResponse.createBySuccessMsg("验证码正确");
    }


    @RequestMapping(value = "get_user_info.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> getUserInfo(HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute(Const.ID);
        return iUserService.getUserInfo(userId);
    }

    @RequestMapping(value = "update_user_info.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> updateUserInfo(HttpServletRequest request, User user) {
        Integer userId = (Integer) request.getAttribute(Const.ID);
        user.setId(userId);
        return iUserService.updateUserInfo(user);
    }

}
