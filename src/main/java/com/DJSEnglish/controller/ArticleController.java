package com.DJSEnglish.controller;

import com.DJSEnglish.common.Const;
import com.DJSEnglish.common.ServerResponse;
import com.DJSEnglish.service.IArticleService;
import com.DJSEnglish.util.JWTUtil;
import com.auth0.jwt.interfaces.Claim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RequestMapping("/article/")
@Controller
public class ArticleController {

    @Autowired
    private IArticleService iArticleService;



    @RequestMapping(value = "get_list.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getList( @RequestParam(required = false, defaultValue = "1") Integer pageNum, @RequestParam(required = false, defaultValue = "10")Integer pageSize)
    {
        return iArticleService.getList(pageNum, pageSize);
    }

    @RequestMapping(value = "get_detail.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getDetail(HttpServletRequest request, HttpServletResponse response, Integer articleId)
    {
        String token = request.getHeader("token");
        Integer userId = null;
        if (token != null)
        {
            Map<String, Claim> map = null;
            try {
                map = JWTUtil.verifyToken(token);
            } catch (Exception e) {
                try {
                    request.getRequestDispatcher("/user/need_login.do").forward(request, response);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
            userId = map.get("id").asInt();
        }
        return iArticleService.getDetail(articleId, userId);
    }

    @RequestMapping(value = "like_article.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse likeComment(HttpServletRequest request, Integer articleId)
    {
        Integer userId = (Integer) request.getAttribute(Const.ID);
        return iArticleService.likeArticle(userId, articleId);
    }

    @RequestMapping(value = "dislike_article.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse dislikeComment(HttpServletRequest request, Integer articleId)
    {
        Integer userId = (Integer) request.getAttribute(Const.ID);
        return iArticleService.dislikeArticle(userId, articleId);
    }

    @RequestMapping(value = "get_collections.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getCollections(HttpServletRequest request)
    {
        Integer userId = (Integer) request.getAttribute(Const.ID);
        return iArticleService.getCollections(userId);
    }

    @RequestMapping(value = "add_collection.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse addCollection(HttpServletRequest request, Integer articleId)
    {
        Integer userId = (Integer) request.getAttribute(Const.ID);
        return iArticleService.collectionArticle(userId, articleId);
    }

    @RequestMapping(value = "del_collection.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse delCollection(HttpServletRequest request, Integer articleId)
    {
        Integer userId = (Integer) request.getAttribute(Const.ID);
        return iArticleService.delColletcion(userId, articleId);
    }
}
