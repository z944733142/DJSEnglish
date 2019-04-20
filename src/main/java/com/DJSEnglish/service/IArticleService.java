package com.DJSEnglish.service;

import com.DJSEnglish.common.ServerResponse;

public interface IArticleService {
    ServerResponse getList(Integer pageNum, Integer pageSize);

    ServerResponse getDetail(Integer articleId, Integer userId);

    ServerResponse likeArticle(Integer userId, Integer articleId);

    ServerResponse dislikeArticle(Integer userId, Integer articleId);

    ServerResponse getCollections(Integer userId);

    ServerResponse collectionArticle(Integer useId, Integer articleId);

    ServerResponse delColletcion(Integer useId, Integer articleId);
}
