package com.DJSEnglish.controller;

import com.DJSEnglish.common.Const;
import com.DJSEnglish.common.ServerResponse;
import com.DJSEnglish.pojo.User;
import com.DJSEnglish.service.IWordService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@RequestMapping("/words/")
@Controller
public class WordsController {

    @Autowired
    private IWordService iWordService;

    @RequestMapping("add_history.do")
    @ResponseBody
    public ServerResponse searchHistory(HttpSession session, String word)
    {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if( user == null)
        {
            return ServerResponse.createByErrorMsg("用户未登录");
        }
        return iWordService.addHistory(user.getId(), word);
    }

    @RequestMapping("delete_history.do")
    @ResponseBody
    public ServerResponse searchHistory(HttpSession session, @RequestParam(value = "word", required = false)String word, @RequestParam(value = "deleteAll", defaultValue = "false") boolean deleteAll) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if( user == null)
        {
            return ServerResponse.createByErrorMsg("用户未登录");
        }
        if(deleteAll)
        {
            return iWordService.deleteAll(user.getId());
        }
        return iWordService.deleteWord(user.getId(), word);
    }

    @RequestMapping("history_list.do")
    @ResponseBody
    public ServerResponse historyList(HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if( user == null)
        {
            return ServerResponse.createByErrorMsg("用户未登录");
        }
        return iWordService.getList(user.getId());
    }
    }
