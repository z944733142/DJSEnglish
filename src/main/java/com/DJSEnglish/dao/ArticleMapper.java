package com.DJSEnglish.dao;

import com.DJSEnglish.pojo.Article;

import java.util.List;

public interface ArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKey(Article record);

    List<Article> getList();

    List<Article> selectByArticleIds(List<Integer> articleIdList);

    int updateByPrimaryKeyAddLike(Integer articleId);

    int updateByPrimaryKeyAddCollection(Integer articleId);

    int updateByPrimaryKeyDisLike(Integer articleId);

    int updateByPrimaryKeyDelCollection(Integer articleId);
}