package com.djsenglish.vo;

public class ArticleVo {

    private Integer id;

    private String text;

    private String begin;

    private String img;

    private Integer collection;

    private boolean isCollection = false;

    private Integer likes;

    private boolean isLike = false;

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

    public boolean getIsCollection() {
        return isCollection;
    }

    public void setIsCollection(boolean collection) {
        isCollection = collection;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public boolean getIsLike() {
        return isLike;
    }

    public void setisLike(boolean like) {
        isLike = like;
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
}
