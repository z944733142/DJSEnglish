package com.DJSEnglish.dao;

public interface article_likeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(article_like record);

    int insertSelective(article_like record);

    article_like selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(article_like record);

    int updateByPrimaryKey(article_like record);
}