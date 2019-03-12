package com.DJSEnglish.controller;

import com.DJSEnglish.common.Const;
import com.DJSEnglish.common.ServerResponse;
import com.DJSEnglish.dao.UserMapper;
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

    @Autowired
    private UserMapper userMapper;

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

    @RequestMapping(value = "logout.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse logout(HttpSession session)
    {
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess();
    }

    @RequestMapping(value = "login_reset_password.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse loginResetPassword(HttpSession session, String password)
    {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        ServerResponse serverResponse;
        if(user != null)
        {
            return ServerResponse.createBySuccess(user);
        }
        if((serverResponse = iUserService.loginResetPassword(user.getId(), password)).isSuccess());
        {
            session.removeAttribute(Const.CURRENT_USER);
        }
        return serverResponse;
    }


    @RequestMapping(value = "register.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse register(User user)
    {
        return iUserService.Register(user);
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
            return ServerResponse.createBySuccessMsg("用户未登录, 请先登录");
        }
        ServerResponse serverResponse = iUserService.updateUserInfo(user);
        if(serverResponse.isSuccess())
        {
            session.setAttribute(Const.CURRENT_USER, userMapper.selectByPrimaryKey(user.getId()));
        }
        return serverResponse;
    }

}
