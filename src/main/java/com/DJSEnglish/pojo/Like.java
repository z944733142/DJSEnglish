package com.DJSEnglish.pojo;

import java.util.Date;

public class Like {
    private Integer id;

    private Integer user;

    private Integer type;

    private Integer articlId;

    private Date updateTime;

    private Date createTime;

    public Like(Integer id, Integer user, Integer type, Integer articlId, Date updateTime, Date createTime) {
        this.id = id;
        this.user = user;
        this.type = type;
        this.articlId = articlId;
        this.updateTime = updateTime;
        this.createTime = createTime;
    }

    public Like() {
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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