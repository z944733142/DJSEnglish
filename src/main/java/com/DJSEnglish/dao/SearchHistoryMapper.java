package com.DJSEnglish.dao;

import com.DJSEnglish.pojo.SearchHistory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SearchHistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SearchHistory record);

    int insertSelective(SearchHistory record);

    SearchHistory selectByPrimaryKey(Integer id);

    List<SearchHistory> selectByUserId(Integer userId);

    int updateByPrimaryKeySelective(SearchHistory record);

    int updateByPrimaryKey(SearchHistory record);

    int deleteAllByUserId(Integer userId);

    int deleteWord(@Param("userId") Integer userId, @Param("word") String  word);
}