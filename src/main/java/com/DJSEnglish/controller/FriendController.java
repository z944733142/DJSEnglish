package com.DJSEnglish.controller;

import com.DJSEnglish.common.Const;
import com.DJSEnglish.common.ServerResponse;
import com.DJSEnglish.service.IFriendService;
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
        Integer id = (Integer) request.getAttribute(Const.ID);
        return iFriendService.concern(id, friendId);
    }

    @RequestMapping("unconcern.do")
    @ResponseBody
    public ServerResponse unconcern(HttpServletRequest request, Integer friendId) {
        Integer id = (Integer) request.getAttribute(Const.ID);
        return iFriendService.unconcern(id, friendId);
    }

    @RequestMapping("check_concern.do")
    @ResponseBody
    public ServerResponse checkConcern(HttpServletRequest request, Integer friendId) {
        Integer id = (Integer) request.getAttribute(Const.ID);
        return iFriendService.checkConcern(id, friendId);
    }
    @RequestMapping("get_list.do")
    @ResponseBody
    public ServerResponse getList(HttpServletRequest request) {
        Integer id = (Integer) request.getAttribute(Const.ID);
        return iFriendService.getList(id);
    }

    @RequestMapping("get_detail.do")
    @ResponseBody
    public ServerResponse getDetail(Integer friendId) {
        
        return iFriendService.getDetail(friendId);
    }
    }
