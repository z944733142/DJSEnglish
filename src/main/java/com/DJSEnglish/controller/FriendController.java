package com.DJSEnglish.controller;

import com.DJSEnglish.common.Const;
import com.DJSEnglish.common.ServerResponse;
import com.DJSEnglish.pojo.User;
import com.DJSEnglish.service.IFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@RequestMapping("/friend/")
@Controller
public class FriendController {

    @Autowired
    private IFriendService iFriendService;

    @RequestMapping("concern.do")
    @ResponseBody
    public ServerResponse concern(HttpSession session, Integer friendId)
    {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if( user == null)
        {
            return ServerResponse.createByErrorMsg("用户未登录");
        }
        return iFriendService.concern(user.getId(), friendId);
    }

    @RequestMapping("unconcern.do")
    @ResponseBody
    public ServerResponse unconcern(HttpSession session, Integer friendId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if( user == null)
        {
            return ServerResponse.createByErrorMsg("用户未登录");
        }
        return iFriendService.unconcern(user.getId(), friendId);
    }

    @RequestMapping("check_concern.do")
    @ResponseBody
    public ServerResponse checkConcern(HttpSession session, Integer friendId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if( user == null)
        {
            return ServerResponse.createByErrorMsg("用户未登录");
        }
        return iFriendService.checkConcern(user.getId(), friendId);
    }
    @RequestMapping("get_list.do")
    @ResponseBody
    public ServerResponse getList(HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if( user == null)
        {
            return ServerResponse.createByErrorMsg("用户未登录");
        }
        return iFriendService.getList(user.getId());
    }

    @RequestMapping("get_detail.do")
    @ResponseBody
    public ServerResponse getDetail(HttpSession session, Integer friendId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if( user == null)
        {
            return ServerResponse.createByErrorMsg("用户未登录");
        }
        return iFriendService.getDetail(friendId);
    }
    }
