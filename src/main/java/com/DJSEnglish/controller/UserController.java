package com.DJSEnglish.controller;

import com.DJSEnglish.common.Const;
import com.DJSEnglish.common.ServerResponse;
import com.DJSEnglish.pojo.User;
import com.DJSEnglish.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @RequestMapping(value = "login.html", method = RequestMethod.POST)
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

    @RequestMapping(value = "login.html", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse logout(HttpSession session)
    {
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess();
    }

    @RequestMapping(value = "get_user_info.html", method = RequestMethod.POST)
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
}
