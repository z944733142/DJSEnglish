package com.djsenglish.pojo;



import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class SearchHistory {

    @JsonIgnore
    private Integer id;

    private Integer user;

    private String word;

    @JsonIgnore
    private Date updateTime;

    @JsonIgnore
    private Date createTime;

    public SearchHistory(Integer id, Integer user, String word, Date updateTime, Date createTime) {
        this.id = id;
        this.user = user;
        this.word = word;
        this.updateTime = updateTime;
        this.createTime = createTime;
    }

    public SearchHistory() {
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

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word == null ? null : word.trim();
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