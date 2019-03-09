package com.DJSEnglish.pojo;

import java.util.Date;

public class Sentences {
    private Integer id;

    private String sentence;

    private Date updateTime;

    private Date createTime;

    public Sentences(Integer id, String sentence, Date updateTime, Date createTime) {
        this.id = id;
        this.sentence = sentence;
        this.updateTime = updateTime;
        this.createTime = createTime;
    }

    public Sentences() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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