package com.DJSEnglish.dao;

import com.DJSEnglish.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectUser(@Param("username") String username, @Param("password") String password);

    int selectUserCount(String username);

    int selectEmailCount(String email);

    int selectPhoneCount(String phone);

    int updateByPhone(@Param("phoneNumber")String phoneNumber, @Param("password")String password);
}