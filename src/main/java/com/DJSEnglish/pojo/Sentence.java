package com.djsenglish.pojo;



import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class Sentence {
    private Integer id;

    private Integer userId;

    private String sentence;

    @JsonIgnore
    private Date updateTime;

    @JsonIgnore
    private Date createTime;

    public Sentence(Integer id, Integer userId, String sentence, Date updateTime, Date createTime) {
        this.id = id;
        this.userId = userId;
        this.sentence = sentence;
        this.updateTime = updateTime;
        this.createTime = createTime;
    }

    public Sentence() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence == null ? null : sentence.trim();
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