package com.DJSEnglish.dao;

public interface comment_likeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(comment_like record);

    int insertSelective(comment_like record);

    comment_like selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(comment_like record);

    int updateByPrimaryKey(comment_like record);
}