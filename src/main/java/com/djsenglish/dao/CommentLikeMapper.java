package com.djsenglish.dao;

import com.djsenglish.pojo.CommentLike;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentLikeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommentLike record);

    int insertSelective(CommentLike record);

    CommentLike selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommentLike record);

    int updateByPrimaryKey(CommentLike record);

    List<Integer> selectList(Integer userId);

    boolean deleteByUserAndComment(@Param("userId") Integer userId, @Param("commentId") Integer commentId);

    int selectCount(@Param("userId") Integer userId, @Param("commentId") Integer commentId);

    int selectCommentId(Integer commentId);
}