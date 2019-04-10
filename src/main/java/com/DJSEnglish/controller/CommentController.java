package com.DJSEnglish.controller;

import com.DJSEnglish.common.Const;
import com.DJSEnglish.common.ServerResponse;
import com.DJSEnglish.pojo.ArticleComment;
import com.DJSEnglish.pojo.User;
import com.DJSEnglish.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@RequestMapping("/comment/")
@Controller
public class CommentController {

    @Autowired
    private ICommentService iCommentService;

    @RequestMapping(value = "get_list.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getList(@RequestParam(required = false, defaultValue = "1") Integer pageNum, @RequestParam(required = false, defaultValue = "10")Integer pageSize)
    {
        return iCommentService.getList(pageNum, pageSize);
    }

    @RequestMapping(value = "add_comment.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse addComment(HttpSession session, ArticleComment articleComment)
    {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if( user == null)
        {
            return ServerResponse.createByErrorMsg("用户未登录");
        }
        return iCommentService.addComment(articleComment);
    }
}
