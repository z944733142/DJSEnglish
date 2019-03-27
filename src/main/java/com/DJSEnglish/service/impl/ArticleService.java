package com.DJSEnglish.service.impl;

import com.DJSEnglish.common.ServerResponse;
import com.DJSEnglish.dao.ArticleMapper;
import com.DJSEnglish.pojo.Article;
import com.DJSEnglish.service.IArticleService;
import com.DJSEnglish.util.DateTimeUtil;
import com.DJSEnglish.vo.ArticleVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("iArticleService")
public class ArticleService implements IArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public ServerResponse getList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Article> articles = articleMapper.getList();
        List<ArticleVo> articleVos = new ArrayList<>();
        if(articles == null || articles.size() <= 0)
        {
            return ServerResponse.createByErrorMsg("页码超出范围");
        }
        for (Article article : articles) {
            articleVos.add(toArticleVo(article));
        }
        PageInfo articlePage = new PageInfo(articleVos);
        return ServerResponse.createBySuccess(articlePage);
    }

    @Override
    public ServerResponse getDetail(Integer articleId)
    {
        Article article = articleMapper.selectByPrimaryKey(articleId);
//        article.setCreateTime(DateTimeUtil.strToDate(article.getCreateTime().toString()));
//        article.setUpdateTime(DateTimeUtil.strToDate(article.getUpdateTime().toString()));
        ArticleVo articleVo = toArticleVo(article);
        return ServerResponse.createBySuccess(articleVo);
    }

    public ArticleVo toArticleVo(Article article)
    {
        ArticleVo articleVo = new ArticleVo();
        articleVo.setBegin(article.getText().substring(50));
        articleVo.setCreateTime(DateTimeUtil.dateToStr(article.getCreateTime()));
        articleVo.setUpdateTime(DateTimeUtil.dateToStr(article.getUpdateTime()));
        articleVo.setCollection(article.getCollection());
        articleVo.setId(article.getId());
        articleVo.setImg(article.getImg());
        articleVo.setLikes(article.getLikes());
        articleVo.setText(article.getText());
        return  articleVo;
    }
}
