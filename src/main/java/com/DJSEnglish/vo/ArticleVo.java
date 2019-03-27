package com.DJSEnglish.vo;

import java.util.Date;

public class ArticleVo {

    private Integer id;

    private String text;

    private String begin;

    private String img;

    private Integer collection;

    private Integer likes;

    private String updateTime;

    private String createTime;

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
        this.text = text;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public ArticleVo() {
    }


    public ArticleVo(Integer id, String text, String begin, String img, Integer collection, Integer likes, String updateTime, String createTime) {
        this.id = id;
        this.text = text;
        this.begin = begin;
        this.img = img;
        this.collection = collection;
        this.likes = likes;
        this.updateTime = updateTime;
        this.createTime = createTime;
    }
}
