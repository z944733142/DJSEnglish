package com.DJSEnglish.service;

import com.DJSEnglish.common.ServerResponse;

public interface IArticleService {
    ServerResponse getList(Integer pageNum, Integer pageSize);

    ServerResponse getDetail(Integer articleId, Integer userId);

    ServerResponse likeArticle(Integer id, Integer articleId);

    ServerResponse dislikeArticle(Integer id, Integer articleId);

    ServerResponse getCollections(Integer userId);

    ServerResponse collectionArticle(Integer useId, Integer articleId);

    ServerResponse delColletcion(Integer useId, Integer articleId);
}
