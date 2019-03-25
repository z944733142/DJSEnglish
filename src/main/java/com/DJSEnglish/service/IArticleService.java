package com.DJSEnglish.service;

import com.DJSEnglish.common.ServerResponse;

public interface IArticleService {
    ServerResponse getList(Integer pageNum, Integer pageSize);

    ServerResponse getDetail(Integer articleId);
}
