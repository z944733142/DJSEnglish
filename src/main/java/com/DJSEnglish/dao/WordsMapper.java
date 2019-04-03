package com.DJSEnglish.dao;

import com.DJSEnglish.pojo.Words;

import java.util.List;

public interface WordsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Words record);

    int insertSelective(Words record);

    Words selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Words record);

    int updateByPrimaryKey(Words record);

    List<Words> selectWordsList();

}