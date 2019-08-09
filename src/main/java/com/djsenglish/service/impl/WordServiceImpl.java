package com.djsenglish.service.impl;

import com.djsenglish.common.ServerResponse;
import com.djsenglish.dao.SearchHistoryMapper;
import com.djsenglish.dao.SentenceMapper;
import com.djsenglish.dao.WordsMapper;
import com.djsenglish.pojo.SearchHistory;
import com.djsenglish.pojo.Sentence;
import com.djsenglish.pojo.Words;
import com.djsenglish.service.IWordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author shuo
 */
@Service("iWordService")
public class WordServiceImpl implements IWordService {

    @Resource
    private SearchHistoryMapper searchHistoryMapper;

    @Resource
    private WordsMapper wordsMapper;

    @Resource
    private SentenceMapper sentenceMapper;

    @Transactional(rollbackFor = Exception.class)
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
        if(sentenceMapper.insertSelective(s) > 0)
        {
            return ServerResponse.createBySuccess("添加成功");
        }
        return ServerResponse.createByErrorMsg("添加失败");
    }

    @Override
    public ServerResponse getSentences(Integer userId) {
        List<Sentence> list = sentenceMapper.selectSentences(userId);
        if(list != null || list.size() > 0)
        {
            return ServerResponse.createBySuccess(list);
        }
        return ServerResponse.createByErrorMsg("数量为零");
    }

//    public ServerResponse deleteHistory(Integer )
}
