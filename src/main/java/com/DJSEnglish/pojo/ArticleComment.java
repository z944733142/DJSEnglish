package com.DJSEnglish.pojo;

import java.util.Date;

public class ArticleComment {
    private Integer id;

    private Integer article;

    private Integer user;

    private String text;

    private Integer likes;

    private Date updateTime;

    private Date createTime;

    public ArticleComment(Integer id, Integer article, Integer user, String text, Integer likes, Date updateTime, Date createTime) {
        this.id = id;
        this.article = article;
        this.user = user;
        this.text = text;
        this.likes = likes;
        this.updateTime = updateTime;
        this.createTime = createTime;
    }

    public ArticleComment() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticle() {
        return article;
    }

    public void setArticle(Integer article) {
        this.article = article;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}