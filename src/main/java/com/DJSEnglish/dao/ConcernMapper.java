package com.DJSEnglish.dao;

import com.DJSEnglish.pojo.Concern;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConcernMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Concern record);

    int insertSelective(Concern record);

    Concern selectByPrimaryKey(Integer id);

    int selectCount(@Param("userId") Integer userId, @Param("friendId") Integer friendId);

    List<Integer> selectByUserId(Integer userId);

    int updateByPrimaryKeySelective(Concern record);

    int updateByPrimaryKey(Concern record);

    int deleteConcern(@Param(value = "userId")Integer userId, @Param(value = "friendId") Integer friendId);
}