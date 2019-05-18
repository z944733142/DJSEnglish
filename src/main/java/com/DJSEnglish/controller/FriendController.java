package com.djsenglish.controller;

import com.djsenglish.common.Const;
import com.djsenglish.common.ServerResponse;
import com.djsenglish.service.IFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/friend/")
@Controller
public class FriendController {

    @Autowired
    private IFriendService iFriendService;

    @RequestMapping("concern.do")
    @ResponseBody
    public ServerResponse concern(HttpServletRequest request, Integer friendId)
    {
        Integer userId = (Integer) request.getAttribute(Const.ID);
        return iFriendService.concern(userId, friendId);
    }

    @RequestMapping("unconcern.do")
    @ResponseBody
    public ServerResponse unconcern(HttpServletRequest request, Integer friendId) {
        Integer userId = (Integer) request.getAttribute(Const.ID);
        return iFriendService.unconcern(userId, friendId);
    }

    @RequestMapping("check_concern.do")
    @ResponseBody
    public ServerResponse checkConcern(HttpServletRequest request, Integer friendId) {
        Integer userId = (Integer) request.getAttribute(Const.ID);
        return iFriendService.checkConcern(userId, friendId);
    }
    @RequestMapping("get_list.do")
    @ResponseBody
    public ServerResponse getList(HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute(Const.ID);
        return iFriendService.getList(userId);
    }

    @RequestMapping("get_detail.do")
    @ResponseBody
    public ServerResponse getDetail(Integer friendId) {
        
        return iFriendService.getDetail(friendId);
    }
    }
