package com.djsenglish.service;

import com.djsenglish.common.ServerResponse;

public interface IWordService {

    ServerResponse addHistory(Integer userId, String word);

    ServerResponse deleteWord(Integer userId, String word);

    ServerResponse deleteAll(Integer userId);

    ServerResponse getList(Integer userId);

    ServerResponse getWord();

    ServerResponse addSentence(Integer userId, String sentence);

    ServerResponse getSentences(Integer userId);
}
