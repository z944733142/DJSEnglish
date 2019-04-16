package com.DJSEnglish.service.impl;


import com.DJSEnglish.common.ServerResponse;
import com.DJSEnglish.dao.ArticleCommentMapper;
import com.DJSEnglish.dao.CommentLikeMapper;
import com.DJSEnglish.pojo.ArticleComment;
import com.DJSEnglish.pojo.CommentLike;
import com.DJSEnglish.service.ICommentService;
import com.DJSEnglish.util.DateTimeUtil;
import com.DJSEnglish.vo.CommentVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("iCommentService")
public class CommentServiceImpl implements ICommentService {
    @Autowired
    private ArticleCommentMapper articleCommentMapper;

    @Autowired
    private CommentLikeMapper commentLikeMapper;

    @Override
    public ServerResponse getList(Integer pageNum, Integer pageSize, Integer userId, Integer articleId) {
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleComment> list = articleCommentMapper.selectCommentList(articleId);
        if(list == null ||list.size() <= 0)
        {
            return ServerResponse.createByErrorMsg("数据为空");
        }
        List<Integer> likeList = commentLikeMapper.selectList(userId);
        PageInfo pageInfo = new PageInfo(list);
        pageInfo.setList(commentToVo(list, userId, likeList));
        return ServerResponse.createBySuccess(pageInfo);
    }

    private List<CommentVo> commentToVo(List<ArticleComment> list, Integer userId, List<Integer> likeList)
    {
        List<CommentVo> voList = new ArrayList<>();

        for (ArticleComment articleComment : list) {
//            if(likeList.contains())
            CommentVo commentVo = new CommentVo();
            if(likeList.contains(articleComment.getId()))
            {
                commentVo.setIslike(true);
            }
            commentVo.setDelete(userId.equals(articleComment.getUser()));
            commentVo.setId(articleComment.getId());
            commentVo.setArticle(articleComment.getArticle());
            commentVo.setLikes(articleComment.getLikes());
            commentVo.setText(articleComment.getText());
            commentVo.setArticle(articleComment.getArticle());
            commentVo.setUser(articleComment.getUser());
            commentVo.setCreateTime(DateTimeUtil.dateToStr(articleComment.getCreateTime()));
            commentVo.setUpdateTime(DateTimeUtil.dateToStr(articleComment.getUpdateTime()));
            commentVo.setId(articleComment.getId());
            voList.add(commentVo);
        }
        return voList;
    }

    @Override
    public ServerResponse addComment(ArticleComment articleComment) {
        if(articleCommentMapper.insertSelective(articleComment) > 0) {
            return ServerResponse.createBySuccessMsg("添加成功");
        }
        return ServerResponse.createByErrorMsg("插入失败");
    }

    @Override
    public ServerResponse delComment(Integer id, Integer userId) {
        if(articleCommentMapper.deleteComment(id, userId))
        {
            return ServerResponse.createBySuccessMsg("删除成功");
        }
        return ServerResponse.createByErrorMsg("删除失败");
    }

    @Override
    public ServerResponse likeComment(Integer userId, Integer commentId)
    {
        CommentLike commentLike = new CommentLike();
        ArticleComment articleComment = new ArticleComment();
        articleComment.setId(commentId);
        commentLike.setCommentId(commentId);
        commentLike.setUser(userId);
        if(commentLikeMapper.selectCount(userId, commentId) > 0)
        {
            return ServerResponse.createBySuccessMsg("请勿重复点赞");
        }
        if(commentLikeMapper.insertSelective(commentLike) > 0)
        {
            articleCommentMapper.updateByPrimaryKeyAddLike(commentId);
            return ServerResponse.createBySuccessMsg("点赞成功");
        }
        return ServerResponse.createByErrorMsg("点赞失败");
    }

    @Override
    public ServerResponse dislikeComment(Integer userId, Integer commentId) {
        if(commentLikeMapper.deleteByUserAndComment(userId, commentId))
        {
            articleCommentMapper.updateByPrimaryKeyDisLike(commentId);
            return ServerResponse.createBySuccessMsg("取消成功");
        }
        return ServerResponse.createByErrorMsg("取消失败");
    }


}
