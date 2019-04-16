package com.DJSEnglish.dao;

import com.DJSEnglish.pojo.Collection;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CollectionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Collection record);

    int insertSelective(Collection record);

    Collection selectByPrimaryKey(Integer id);

    int selectById(@Param("userId") Integer userId, @Param("articleId") Integer articleId);

    int updateByPrimaryKeySelective(Collection record);

    int updateByPrimaryKey(Collection record);

    List<Integer> selectByUserId(Integer userId);

    boolean deleteByUserAndArticle(@Param("userId")Integer userId, @Param("articleId")Integer articleId);
}