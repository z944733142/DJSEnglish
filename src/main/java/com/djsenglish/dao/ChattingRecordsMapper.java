package com.djsenglish.dao;

import com.djsenglish.pojo.ChattingRecords;

public interface ChattingRecordsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ChattingRecords record);

    int insertSelective(ChattingRecords record);

    ChattingRecords selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ChattingRecords record);

    int updateByPrimaryKey(ChattingRecords record);
}