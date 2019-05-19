package com.djsenglish.dao;

import com.djsenglish.pojo.ArticleLike;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleLikeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleLike record);

    int insertSelective(ArticleLike record);

    ArticleLike selectByPrimaryKey(Integer id);

    List<Integer> selectList(Integer userId);

    int selectById(@Param("userId") Integer userId, @Param("articleId") Integer articleId);

    int updateByPrimaryKeySelective(ArticleLike record);

    int updateByPrimaryKey(ArticleLike record);

    boolean deleteByUserAndArticle(@Param("userId") Integer userId, @Param("articleId") Integer articleId);

    int selectCount(@Param("userId") Integer userId, @Param("articleId") Integer articleId);
}