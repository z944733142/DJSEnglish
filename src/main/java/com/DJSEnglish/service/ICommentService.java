package com.DJSEnglish.service;

import com.DJSEnglish.common.ServerResponse;
import com.DJSEnglish.pojo.ArticleComment;

public interface ICommentService {


    ServerResponse getList(Integer pageNum, Integer pageSize);

    ServerResponse addComment(ArticleComment articleComment);
}
