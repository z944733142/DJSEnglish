package com.djsenglish.pojo;

import java.util.Date;

public class Collection {
    private Integer id;

    private Integer user;

    private Integer article;

    private Date updateTime;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Integer getArticle() {
        return article;
    }

    public void setArticle(Integer article) {
        this.article = article;
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

    public Collection(Integer id, Integer user, Integer article, Date updateTime, Date createTime) {
        this.id = id;
        this.user = user;
        this.article = article;
        this.updateTime = updateTime;
        this.createTime = createTime;
    }

    public Collection() {
    }
}