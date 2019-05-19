package com.djsenglish.pojo;

import java.util.Date;

public class CommentLike {
    private Integer id;

    private Integer user;

    private Integer commentId;

    private Date updateTime;

    private Date createTime;

    public CommentLike(Integer id, Integer user, Integer commentId, Date updateTime, Date createTime) {
        this.id = id;
        this.user = user;
        this.commentId = commentId;
        this.updateTime = updateTime;
        this.createTime = createTime;
    }

    public CommentLike() {
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

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer articlId) {
        this.commentId = articlId;
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