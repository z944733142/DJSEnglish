package com.DJSEnglish.dao;

import com.DJSEnglish.vo.FriendListVo;

import java.util.List;

public interface LikeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Like record);

    int insertSelective(Like record);

    Like selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Like record);

    int updateByPrimaryKey(Like record);

    List<FriendListVo> selectFriendList(List<Integer> idList);
}