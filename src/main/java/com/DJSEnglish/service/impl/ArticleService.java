package com.DJSEnglish.service.impl;

import com.DJSEnglish.common.ServerResponse;
import com.DJSEnglish.dao.ArticleMapper;
import com.DJSEnglish.pojo.Article;
import com.DJSEnglish.service.IArticleService;
import com.DJSEnglish.util.DateTimeUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("iArticleService")
public class ArticleService implements IArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public ServerResponse getList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Article> articles = articleMapper.getList();
        for (Article article : articles) {
            System.out.println(article.getCreateTime());
            System.out.println(article.getUpdateTime());
        }

        if(articles == null || articles.size() <= 0)
        {
            return ServerResponse.createByErrorMsg("页码超出范围");
        }
        PageInfo articlePage = new PageInfo(articles);
        return ServerResponse.createBySuccess(articlePage);
    }

    @Override
    public ServerResponse getDetail(Integer articleId)
    {
        Article article = articleMapper.selectByPrimaryKey(articleId);
//        article.setCreateTime(DateTimeUtil.strToDate(article.getCreateTime().toString()));
//        article.setUpdateTime(DateTimeUtil.strToDate(article.getUpdateTime().toString()));
        return ServerResponse.createBySuccess(article);
    }
}
