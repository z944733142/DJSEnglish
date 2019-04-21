package com.DJSEnglish.controller;

import com.DJSEnglish.common.Const;
import com.DJSEnglish.common.ServerResponse;
import com.DJSEnglish.pojo.ArticleComment;
import com.DJSEnglish.pojo.User;
import com.DJSEnglish.service.ICommentService;
import com.DJSEnglish.util.JWTUtil;
import com.auth0.jwt.interfaces.Claim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RequestMapping("/comment/")
@Controller
public class CommentController {

    @Autowired
    private ICommentService iCommentService;


    @RequestMapping(value = "get_list.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getList(HttpServletRequest request, @RequestParam(required = false, defaultValue = "1") Integer pageNum, @RequestParam(required = false, defaultValue = "10")Integer pageSize, Integer articleId)
    {
        String token = request.getHeader("token");
        Integer userId = null;
        if (token != null)
        {
            Map<String, Claim> map = null;
            try {
                map = JWTUtil.verifyToken(token);
            } catch (Exception e) {
            }
            userId = map.get("id").asInt();
        }
        return iCommentService.getList(pageNum, pageSize, userId, articleId);
    }

    @RequestMapping(value = "add_comment.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse addComment(HttpServletRequest request, ArticleComment articleComment)
    {
        Integer userId = (Integer) request.getAttribute(Const.ID);
        articleComment.setUser(userId);
        return iCommentService.addComment(articleComment);
    }

    @RequestMapping(value = "del_comment.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse delComment(HttpServletRequest request, Integer commentId)
    {
        Integer userId = (Integer) request.getAttribute(Const.ID);
        return iCommentService.delComment(commentId, userId);
    }

    @RequestMapping(value = "like_comment.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse likeComment(HttpServletRequest request, Integer commentId)
    {
        Integer userId = (Integer) request.getAttribute(Const.ID);
        return iCommentService.likeComment(userId, commentId);
    }

    @RequestMapping(value = "dislike_comment.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse dislikeComment(HttpServletRequest request, Integer commentId)
    {
        Integer userId = (Integer) request.getAttribute(Const.ID);
        return iCommentService.dislikeComment(userId, commentId);
    }


}
