package com.djsenglish.dao;

import com.djsenglish.pojo.Sentence;

import java.util.List;

public interface SentenceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Sentence record);

    int insertSelective(Sentence record);

    Sentence selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Sentence record);

    int updateByPrimaryKey(Sentence record);

    List<Sentence> selectSentences(Integer userId);
}