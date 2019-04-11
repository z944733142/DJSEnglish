package com.DJSEnglish.dao;

import com.DJSEnglish.pojo.ArticleComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleComment record);

    int insertSelective(ArticleComment record);

    ArticleComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleComment record);

    int updateByPrimaryKey(ArticleComment record);

    List<ArticleComment> selectCommentList();

    boolean deleteComment(@Param(value = "id") Integer id, @Param(value = "userId") Integer userId);
}