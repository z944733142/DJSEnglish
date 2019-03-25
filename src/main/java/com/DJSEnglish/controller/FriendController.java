package com.DJSEnglish.controller;

import com.DJSEnglish.common.Const;
import com.DJSEnglish.common.ServerResponse;
import com.DJSEnglish.pojo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@RequestMapping("/friend/")
public class FriendController {


    @RequestMapping("concern")
    @ResponseBody
    public ServerResponse concern(HttpSession session, String friend)
    {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if( user == null)
        {
            return ServerResponse.createByErrorMsg("用户未登录");
        }
        return null;
    }


}
