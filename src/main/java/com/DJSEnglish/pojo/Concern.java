package com.djsenglish.pojo;

import java.util.Date;

public class Concern {
    private Integer id;

    private Integer user;

    private Integer friending;

    private Date updateTime;

    private Date createTime;

    public Concern(Integer id, Integer user, Integer friending, Date updateTime, Date createTime) {
        this.id = id;
        this.user = user;
        this.friending = friending;
        this.updateTime = updateTime;
        this.createTime = createTime;
    }

    public Concern() {
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

    public Integer getFriending() {
        return friending;
    }

    public void setFriending(Integer friending) {
        this.friending = friending;
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