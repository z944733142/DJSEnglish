package com.DJSEnglish.service.impl;

import com.DJSEnglish.common.ServerResponse;
import com.DJSEnglish.dao.SearchHistoryMapper;
import com.DJSEnglish.pojo.SearchHistory;
import com.DJSEnglish.service.IWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("iWordService")
public class WordServiceImpl implements IWordService {

    @Autowired
    private SearchHistoryMapper searchHistoryMapper;

    @Override
    public ServerResponse addHistory(Integer id, String word)
    {
        SearchHistory searchHistory = new SearchHistory();
        searchHistory.setUser(id);
        searchHistory.setWord(word);
        if(searchHistoryMapper.insertSelective(searchHistory) > 0)
        {
            return ServerResponse.createBySuccessMsg("插入成功");
        }
        return ServerResponse.createByErrorMsg("插入失败");
    }

    @Override
    public ServerResponse deleteWord(Integer userId, String word) {
        if(searchHistoryMapper.deleteWord(userId, word) > 0)
        {
            return ServerResponse.createBySuccessMsg("删除成功");
        }
        return ServerResponse.createByErrorMsg("删除失败");
    }

    @Override
    public ServerResponse deleteAll(Integer userId) {
        if(searchHistoryMapper.deleteAllByUserId(userId) > 0)
        {
            return ServerResponse.createBySuccessMsg("成功删除");
        }
        return ServerResponse.createByErrorMsg(("清空0条"));
    }

    @Override
    public ServerResponse getList(Integer id) {
        List<SearchHistory> list = searchHistoryMapper.selectByUserId(id);
        if(list == null || list.size() == 0)
        {
            return ServerResponse.createByErrorMsg("数量为零");
        }
        return ServerResponse.createBySuccess(list);
    }


//    public ServerResponse deleteHistory(Integer )
}
