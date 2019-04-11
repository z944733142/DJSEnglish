package com.DJSEnglish.pojo;

import java.util.Date;

public class ArticleLike {
    private Integer id;

    private Integer user;

    private Integer articlId;

    private Date updateTime;

    private Date createTime;

    public ArticleLike(Integer id, Integer user, Integer articlId, Date updateTime, Date createTime) {
        this.id = id;
        this.user = user;
        this.articlId = articlId;
        this.updateTime = updateTime;
        this.createTime = createTime;
    }

    public ArticleLike() {
        super();
    }

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

    public Integer getArticlId() {
        return articlId;
    }

    public void setArticlId(Integer articlId) {
        this.articlId = articlId;
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