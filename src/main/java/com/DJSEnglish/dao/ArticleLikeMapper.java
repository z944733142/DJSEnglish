package com.DJSEnglish.dao;

import com.DJSEnglish.pojo.ArticleLike;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface ArticleLikeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleLike record);

    int insertSelective(ArticleLike record);

    ArticleLike selectByPrimaryKey(Integer id);

    List<Integer> selectList(@Param(value = "userId") Integer userId, @Param(value = "articleId") Integer articleId);

    int selectById(@Param("userId") Integer userId, @Param("articleId")Integer articleId);

    int updateByPrimaryKeySelective(ArticleLike record);

    int updateByPrimaryKey(ArticleLike record);

}