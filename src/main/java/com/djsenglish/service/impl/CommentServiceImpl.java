package com.djsenglish.service.impl;


import com.djsenglish.common.ServerResponse;
import com.djsenglish.dao.ArticleCommentMapper;
import com.djsenglish.dao.ArticleMapper;
import com.djsenglish.dao.CommentLikeMapper;
import com.djsenglish.pojo.ArticleComment;
import com.djsenglish.pojo.CommentLike;
import com.djsenglish.service.ICommentService;
import com.djsenglish.util.DateTimeUtil;
import com.djsenglish.vo.CommentVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shuo
 */
@Service("iCommentService")
public class CommentServiceImpl implements ICommentService {
    @Resource
    private ArticleCommentMapper articleCommentMapper;

    @Resource
    private CommentLikeMapper commentLikeMapper;

    @Resource
    private ArticleMapper articleMapper;

    @Override
    public ServerResponse getList(Integer pageNum, Integer pageSize, Integer userId, Integer articleId) {
        PageHelper.startPage(pageNum, pageSize);
        if(articleMapper.selectByPrimaryKey(articleId) == null)
        {
            return ServerResponse.createByErrorMsg("文章不存在");
        }
        List<ArticleComment> list = articleCommentMapper.selectCommentList(articleId);
        if(list == null ||list.size() <= 0)
        {
            return ServerResponse.createByErrorMsg("暂无评论");
        }
        List<Integer> likeList;
        if(userId != null)
        {
            likeList = commentLikeMapper.selectList(userId);
        }else
        {
            likeList = new ArrayList<>();
        }
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
        articleComment.setLikes(0);
        if(articleMapper.selectByPrimaryKey(articleComment.getArticle()) == null)
        {
            return ServerResponse.createByErrorMsg("文章不存在");
        }
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ServerResponse likeComment(Integer userId, Integer commentId)
    {
        CommentLike commentLike = new CommentLike();
        ArticleComment articleComment = new ArticleComment();
        articleComment.setId(commentId);
        commentLike.setCommentId(commentId);
        commentLike.setUser(userId);
        if(articleCommentMapper.selectCountByPrimaryKey(commentId) == 0)
        {
            return ServerResponse.createByErrorMsg("评论不存在");
        }
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

    @Transactional(rollbackFor = Exception.class)
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
