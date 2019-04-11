package com.DJSEnglish.service;

import com.DJSEnglish.common.ServerResponse;
import com.DJSEnglish.pojo.ArticleComment;

public interface ICommentService {

    ServerResponse getList(Integer pageNum, Integer pageSize, Integer userId);

    ServerResponse addComment(ArticleComment articleComment);

    ServerResponse delComment(Integer id, Integer userId);
}
