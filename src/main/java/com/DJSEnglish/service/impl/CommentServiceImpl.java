package com.DJSEnglish.service.impl;


import com.DJSEnglish.common.ServerResponse;
import com.DJSEnglish.dao.ArticleCommentMapper;
import com.DJSEnglish.pojo.ArticleComment;
import com.DJSEnglish.service.ICommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("iCommentService")
public class CommentServiceImpl implements ICommentService {
    @Autowired
    private ArticleCommentMapper articleCommentMapper;

    @Override
    public ServerResponse getList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleComment> list = articleCommentMapper.selectCommentList();
        if(list == null ||list.size() <= 0)
        {
            return ServerResponse.createByErrorMsg("超出页码范围");
        }
        PageInfo pageInfo = new PageInfo(list);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse addComment(ArticleComment articleComment) {
        return null;
    }
}
