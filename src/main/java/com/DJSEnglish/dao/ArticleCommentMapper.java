package com.DJSEnglish.dao;

import com.DJSEnglish.pojo.ArticleComment;

import java.util.List;

public interface ArticleCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleComment record);

    int insertSelective(ArticleComment record);

    ArticleComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleComment record);

    int updateByPrimaryKey(ArticleComment record);

    List<ArticleComment> selectCommentList();
}