package com.DJSEnglish.service.impl;

import com.DJSEnglish.common.ServerResponse;
import com.DJSEnglish.dao.ArticleLikeMapper;
import com.DJSEnglish.dao.ArticleMapper;
import com.DJSEnglish.dao.CollectionMapper;
import com.DJSEnglish.pojo.Article;
import com.DJSEnglish.pojo.ArticleLike;
import com.DJSEnglish.pojo.Collection;
import com.DJSEnglish.pojo.CommentLike;
import com.DJSEnglish.service.IArticleService;
import com.DJSEnglish.util.DateTimeUtil;
import com.DJSEnglish.vo.ArticleVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("iArticleService")
public class ArticleServiceImpl implements IArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleLikeMapper articleLikeMapper;

    @Autowired
    private CollectionMapper collectionMapper;

    @Override
    public ServerResponse getList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Article> articles = articleMapper.getList();
        List<ArticleVo> articleVos = new ArrayList<>();
        if(articles == null || articles.size() <= 0)
        {
            return ServerResponse.createByErrorMsg("页码超出范围");
        }
        for (Article article : articles) {
            articleVos.add(toArticleVo(article));
        }
        PageInfo articlePage = new PageInfo(articleVos);

        return ServerResponse.createBySuccess(articlePage);
    }

    @Override
    public ServerResponse getDetail(Integer articleId, Integer userId)
    {
        Article article = articleMapper.selectByPrimaryKey(articleId);
        if(article == null)
        {
            return ServerResponse.createByErrorMsg("文章不存在");
        }
//        article.setCreateTime(DateTimeUtil.strToDate(article.getCreateTime().toString()));
//        article.setUpdateTime(DateTimeUtil.strToDate(article.getUpdateTime().toString()));
        ArticleVo articleVo = toArticleVo(article, userId);
        return ServerResponse.createBySuccess(articleVo);
    }

    @Override
    public ServerResponse likeArticle(Integer userId, Integer articleId) {
        ArticleLike articleLike = new ArticleLike();
        Article article = new Article();
        articleLike.setUser(userId);
        articleLike.setArticlId(articleId);
        if(articleLikeMapper.selectCount(userId, articleId) > 0)
        {
            return ServerResponse.createByErrorMsg("已赞过");
        }
        if(articleLikeMapper.insertSelective(articleLike) > 0)
        {
            articleMapper.updateByPrimaryKeyAddLike(articleId);
            return ServerResponse.createBySuccessMsg("点赞成功");
        }
        return ServerResponse.createByErrorMsg("点赞失败");
    }

    @Override
    public ServerResponse dislikeArticle(Integer userId, Integer articleId) {
        if(articleLikeMapper.selectCount(userId, articleId) == 0)
        {
            return ServerResponse.createByErrorMsg("未赞过");
        }
        if(articleLikeMapper.deleteByUserAndArticle(userId, articleId))
        {
            articleMapper.updateByPrimaryKeyDisLike(articleId);
            return ServerResponse.createBySuccessMsg("取消成功");
        }
        return ServerResponse.createByErrorMsg("取消失败");
    }

    @Override
    public ServerResponse getCollections(Integer userId) {

        List<Integer> articleIdList = collectionMapper.selectByUserId(userId);
        List<ArticleVo> articleVos = new ArrayList<>();
        if(articleIdList == null || articleIdList.size() <= 0)
        {
            return  ServerResponse.createBySuccessMsg("数量为零");
        }
        List<Article> articles = articleMapper.selectByArticleIds(articleIdList);
        for (Article article : articles) {
            articleVos.add(toArticleVo(article, userId));
        }

        return ServerResponse.createBySuccess(articleVos);
    }

    @Override
    public ServerResponse collectionArticle(Integer userId, Integer articleId) {
        Collection collection = new Collection();
        collection.setArticle(articleId);
        collection.setUser(userId);
        if(collectionMapper.selectCount(userId, articleId) > 0)
        {
            return ServerResponse.createByErrorMsg("已收藏过");
        }
        if(collectionMapper.insertSelective(collection) > 0)
        {
            articleMapper.updateByPrimaryKeyAddCollection(articleId);
            return ServerResponse.createBySuccessMsg("收藏成功");
        }
        return ServerResponse.createByErrorMsg("收藏失败");
    }

    @Override
    public ServerResponse delColletcion(Integer userId, Integer articleId) {
        if(collectionMapper.selectCount(userId, articleId) == 0)
        {
            return ServerResponse.createByErrorMsg("未收藏过");
        }
        if(collectionMapper.deleteByUserAndArticle(userId, articleId))
        {
            articleMapper.updateByPrimaryKeyDelCollection(articleId);
            return ServerResponse.createBySuccessMsg("取消成功");
        }
        return ServerResponse.createByErrorMsg("取消失败");
    }

    public ArticleVo toArticleVo(Article article, Integer userId)
    {
        ArticleVo articleVo = new ArticleVo();
        int length = article.getText().length();
        if(articleLikeMapper.selectById(userId, article.getId()) > 0)
        {
            articleVo.setisLike(true);
        }
        if(collectionMapper.selectById(userId, article.getId()) > 0)
        {
            articleVo.setIsCollection(true);
        }
        articleVo.setBegin(article.getText().substring(0, length > 50 ? 50 : length));
        articleVo.setCreateTime(DateTimeUtil.dateToStr(article.getCreateTime()));
        articleVo.setUpdateTime(DateTimeUtil.dateToStr(article.getUpdateTime()));
        articleVo.setCollection(article.getCollection());
        articleVo.setId(article.getId());
        articleVo.setImg(article.getImg());
        articleVo.setLikes(article.getLikes());
        articleVo.setText(article.getText());
        return  articleVo;
    }

    public ArticleVo toArticleVo(Article article)
    {
        ArticleVo articleVo = new ArticleVo();
        int length = article.getText().length();
        articleVo.setBegin(article.getText().substring(0, length > 50 ? 50 : length));
        articleVo.setCreateTime(DateTimeUtil.dateToStr(article.getCreateTime()));
        articleVo.setUpdateTime(DateTimeUtil.dateToStr(article.getUpdateTime()));
        articleVo.setCollection(article.getCollection());
        articleVo.setId(article.getId());
        articleVo.setImg(article.getImg());
        articleVo.setLikes(article.getLikes());
        articleVo.setText(article.getText());
        return  articleVo;
    }


}
