package com.DJSEnglish.service.impl;

import com.DJSEnglish.common.ServerResponse;
import com.DJSEnglish.dao.SearchHistoryMapper;
import com.DJSEnglish.dao.WordsMapper;
import com.DJSEnglish.pojo.SearchHistory;
import com.DJSEnglish.pojo.Sentence;
import com.DJSEnglish.pojo.Words;
import com.DJSEnglish.service.IWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("iWordService")
public class WordServiceImpl implements IWordService {

    @Autowired
    private SearchHistoryMapper searchHistoryMapper;

    @Autowired
    private WordsMapper wordsMapper;

    @Autowired
    private SentencesMapper sentencesMapper;

    @Override
    public ServerResponse addHistory(Integer userId, String word)
    {
        SearchHistory searchHistory = new SearchHistory();
        searchHistory.setUser(userId);
        searchHistory.setWord(word);
        if(searchHistoryMapper.selectCountByUseridAndWord(userId, word) > 0)
        {
            if(searchHistoryMapper.updateWordTime(userId, word) > 0)
            {
                return ServerResponse.createBySuccessMsg("插入成功");
            }
            else
            {
                return ServerResponse.createByErrorMsg("插入失败");
            }
        }
        if(searchHistoryMapper.insertSelective(searchHistory) > 0)
        {
            return ServerResponse.createBySuccessMsg("插入成功");
        }
        return ServerResponse.createByErrorMsg("插入失败");
    }

    @Override
    public ServerResponse deleteWord(Integer useruserId, String word) {
        if(searchHistoryMapper.deleteWord(useruserId, word) > 0)
        {
            return ServerResponse.createBySuccessMsg("删除成功");
        }
        return ServerResponse.createByErrorMsg("删除失败");
    }

    @Override
    public ServerResponse deleteAll(Integer useruserId) {
        if(searchHistoryMapper.deleteAllByUserId(useruserId) > 0)
        {
            return ServerResponse.createBySuccessMsg("成功删除");
        }
        return ServerResponse.createByErrorMsg(("清空0条"));
    }

    @Override
    public ServerResponse getList(Integer userId) {
        List<SearchHistory> list = searchHistoryMapper.selectByUserId(userId);
        if(list == null || list.size() == 0)
        {
            return ServerResponse.createByErrorMsg("数量为零");
        }
        return ServerResponse.createBySuccess(list);
    }

    @Override
    public ServerResponse getWord() {
        List<Words> words = wordsMapper.selectWordsList();

        return ServerResponse.createBySuccess(words);
    }

    @Override
    public ServerResponse addSentence(Integer userId, String sentence) {
        Sentence s = new Sentence();
        s.setUserId(userId);
        s.setSentence(sentence);
        if(sentencesMapper.insertSelective(s) > 0)
        {
        return ;
    }

//    public ServerResponse deleteHistory(Integer )
}
