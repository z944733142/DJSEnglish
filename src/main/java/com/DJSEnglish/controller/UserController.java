package com.DJSEnglish.controller;

import com.DJSEnglish.common.Const;
import com.DJSEnglish.common.ServerResponse;
import com.DJSEnglish.dao.UserMapper;
import com.DJSEnglish.pojo.User;
import com.DJSEnglish.service.IFileService;
import com.DJSEnglish.service.IUserService;
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
    public ServerResponse test(String test, HttpServletRequest request, HttpSession session)
    {
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements())
        {
            String nextElement = headerNames.nextElement();
            System.out.println(nextElement + ":" + request.getHeader(nextElement));
        }
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements())
        {
            String nextElement = parameterNames.nextElement();
            System.out.println(nextElement + ":" + request.getParameter(nextElement));
        }
        if(StringUtils.isNotBlank(test)) {
            return ServerResponse.createBySuccessMsg("参数" + test);
        }
        return ServerResponse.createByErrorMsg("失败");
    }

    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse login(String username, String password, HttpSession session)
    {
            ServerResponse serverResponse = iUserService.Login(username, password);
            if(serverResponse.isSuccess())
            {
                session.setAttribute(Const.CURRENT_USER, serverResponse.getData());
            }
        return serverResponse;
    }

    @RequestMapping(value = "upload.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse upload(HttpSession session, @RequestParam(value = "upload_file",required = false) MultipartFile file, HttpServletRequest request)
    {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        ServerResponse serverResponse;
        if(user == null)
        {
            return ServerResponse.createByErrorMsg("用户未登录");
        }
        String path = request.getSession().getServletContext().getRealPath("upload");
        String targetFileName = iFileService.upload(file,path);
        String url = PropertiesUtil.getProperty("ftp.server.http.prefix")+targetFileName;
        if(targetFileName != null)
        {
            User newUser = new User();
            newUser.setImg(targetFileName);
            newUser.setId(user.getId());
            userMapper.updateByPrimaryKeySelective(newUser);
            user.setImg(url);
        }
        Map fileMap = Maps.newHashMap();
        fileMap.put("uri",targetFileName);
        fileMap.put("url",url);
        return ServerResponse.createBySuccess(fileMap);
    }

    @RequestMapping(value = "logout.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse logout(HttpSession session)
    {
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccessMsg("注销成功");
    }

    @RequestMapping(value = "forget_reset_password.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse forgetResetPassword(String msgCode, String phoneNumber, String password)
    {
        if(!PhoneUtil.judgeCodeIsTrue(msgCode, phoneNumber))
        {
            return ServerResponse.createByErrorMsg("验证码错误");
        }
        return iUserService.forgetResetPassword(phoneNumber, password);
    }

    @RequestMapping(value = "get_msgcode.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getMsgcode(String phoneNumber)
    {
        if (PhoneUtil.getVerificationCode(phoneNumber) != null)
        {
            return ServerResponse.createBySuccessMsg("发送成功");
        }
        return ServerResponse.createByErrorMsg("发送失败");
    }

    @RequestMapping(value = "login_reset_password.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse loginResetPassword(HttpSession session, String password)
    {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        ServerResponse serverResponse;
        if(user == null)
        {
            return ServerResponse.createByErrorMsg("用户未登录");
        }
        if((serverResponse = iUserService.loginResetPassword(user.getId(), password)).isSuccess());
        {
            session.removeAttribute(Const.CURRENT_USER);
        }
        return serverResponse;
    }


    @RequestMapping(value = "register.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse register(User user, String msgCode)
    {
        if(!PhoneUtil.judgeCodeIsTrue(msgCode, user.getPhone()))
        {
            return ServerResponse.createByErrorMsg("注册失败, 验证码不正确");
        }
        return iUserService.Register(user);
    }

    @RequestMapping(value = "check_msg.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse checkMsg(String phone, String msgCode)
    {
        if(!PhoneUtil.judgeCodeIsTrue(msgCode, phone))
        {
            return ServerResponse.createByErrorMsg("验证码不正确");
        }
        return ServerResponse.createBySuccessMsg("验证码正确");
    }

//    @RequestMapping(value = "register.do", method = RequestMethod.POST)
//    @ResponseBody
//    public ServerResponse get

    @RequestMapping(value = "get_user_info.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> getUserInfo(HttpSession session)
    {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user != null)
        {
            return ServerResponse.createBySuccess(user);
        }
        return ServerResponse.createByErrorMsg("用户未登录, 无法获取当前用户信息");
    }

    @RequestMapping(value = "update_user_info.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> updateUserInfo(HttpSession session, User user)
    {
        User loginUser = (User) session.getAttribute(Const.CURRENT_USER);

        if(loginUser == null)
        {
            return ServerResponse.createByErrorMsg("用户未登录, 请先登录");
        }
        user.setId(loginUser.getId());
        ServerResponse serverResponse = iUserService.updateUserInfo(user);
        if(serverResponse.isSuccess())
        {
            session.setAttribute(Const.CURRENT_USER, userMapper.selectByPrimaryKey(user.getId()));
        }
        return serverResponse;
    }

}
