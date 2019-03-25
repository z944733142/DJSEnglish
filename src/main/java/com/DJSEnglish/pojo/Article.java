package com.DJSEnglish.pojo;

import java.util.Date;

public class Article {
    private Integer id;

    private String text;

    private String begin;

    private String img;

    private Integer collection;

    private Integer likes;

    private Date updateTime;

    private Date createTime;

    public Article(Integer id, String text, String begin, String img, Integer collection, Integer likes,
                   Date updateTime, Date createTime) {
        this.id = id;
        this.text = text;
        this.begin = begin;
        this.img = img;
        this.collection = collection;
        this.likes = likes;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Article() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin == null ? null : begin.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public Integer getCollection() {
        return collection;
    }

    public void setCollection(Integer collection) {
        this.collection = collection;
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