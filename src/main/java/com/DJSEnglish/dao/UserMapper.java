package com.DJSEnglish.dao;

import com.DJSEnglish.pojo.User;
import com.DJSEnglish.vo.FriendListVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectUser(@Param("phoneNumber") String phoneNumber, @Param("password") String password);

    int selectPhoneCount(String phone);

    int updateByPhone(@Param("phoneNumber")String phoneNumber, @Param("password")String password);

    List<FriendListVo> selectFriendList(List<Integer> idList);

    int selectNameCount(String userName);
}