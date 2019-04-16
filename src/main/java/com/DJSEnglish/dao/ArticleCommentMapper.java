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

    List<ArticleComment> selectCommentList(Integer articleId);

    boolean deleteComment(@Param(value = "id") Integer id, @Param(value = "userId") Integer userId);

    void updateByPrimaryKeyAddLike(Integer commentId);

    void updateByPrimaryKeyDisLike(Integer commentId);
}