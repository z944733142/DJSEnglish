package com.djsenglish.dao;

import com.djsenglish.pojo.QQUser;

public interface QQUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QQUser record);

    int insertSelective(QQUser record);

    QQUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QQUser record);

    int updateByPrimaryKey(QQUser record);
}