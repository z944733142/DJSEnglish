package com.DJSEnglish.controller;

import com.DJSEnglish.common.Const;
import com.DJSEnglish.common.ServerResponse;
import com.DJSEnglish.pojo.User;
import com.DJSEnglish.service.IArticleService;
import com.DJSEnglish.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@RequestMapping("/article/")
@Controller
public class ArticleController {

    @Autowired
    private IArticleService iArticleService;



    @RequestMapping(value = "get_list.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getList(HttpSession session, @RequestParam(required = false, defaultValue = "1") Integer pageNum, @RequestParam(required = false, defaultValue = "10")Integer pageSize)
    {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if( user == null)
        {
            return ServerResponse.createByErrorMsg("用户未登录");
        }
        return iArticleService.getList(pageNum, pageSize);
    }

    @RequestMapping(value = "get_detail.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getDetail(HttpSession session, Integer articleId)
    {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if( user == null)
        {
            return ServerResponse.createByErrorMsg("用户未登录");
        }
        return iArticleService.getDetail(articleId, user.getId());
    }

    @RequestMapping(value = "like_article.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse likeComment(HttpSession session, Integer articleId)
    {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if( user == null)
        {
            return ServerResponse.createByErrorMsg("用户未登录");
        }
        return iArticleService.likeArticle(user.getId(), articleId);
    }

    @RequestMapping(value = "dislike_article.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse dislikeComment(HttpSession session, Integer articleId)
    {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if( user == null)
        {
            return ServerResponse.createByErrorMsg("用户未登录");
        }
        return iArticleService.dislikeArticle(user.getId(), articleId);
    }

    @RequestMapping(value = "get_collections.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getCollections(HttpSession session)
    {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if( user == null)
        {
            return ServerResponse.createByErrorMsg("用户未登录");
        }
        return iArticleService.getCollections(user.getId());
    }

    @RequestMapping(value = "add_collection.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse addCollection(HttpSession session, Integer articleId)
    {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if( user == null)
        {
            return ServerResponse.createByErrorMsg("用户未登录");
        }
        return iArticleService.collectionArticle(user.getId(), articleId);
    }

    @RequestMapping(value = "del_collection.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse delCollection(HttpSession session, Integer articleId)
    {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if( user == null)
        {
            return ServerResponse.createByErrorMsg("用户未登录");
        }
        return iArticleService.delColletcion(user.getId(), articleId);
    }
}
