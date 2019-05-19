package com.djsenglish.dao;

import com.djsenglish.pojo.ArticleComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author shuo
 */

public interface ArticleCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleComment record);

    int insertSelective(ArticleComment record);

    ArticleComment selectByPrimaryKey(Integer id);

    int selectCountByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleComment record);

    int updateByPrimaryKey(ArticleComment record);

    List<ArticleComment> selectCommentList(Integer articleId);

    boolean deleteComment(@Param(value = "id") Integer id, @Param(value = "userId") Integer userId);

    void updateByPrimaryKeyAddLike(Integer commentId);

    void updateByPrimaryKeyDisLike(Integer commentId);
}