package com.DJSEnglish.service;

import com.DJSEnglish.common.ServerResponse;

public interface IWordService {

    ServerResponse addHistory(Integer id, String word);

    ServerResponse deleteWord(Integer id, String word);

    ServerResponse deleteAll(Integer id);

    ServerResponse getList(Integer id);

    ServerResponse getWord();
}
