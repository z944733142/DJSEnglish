package com.DJSEnglish.pojo;

public class Collection {
    private Integer id;

    private Integer user;

    private Integer article;

    public Collection(Integer id, Integer user, Integer article) {
        this.id = id;
        this.user = user;
        this.article = article;
    }

    public Collection() {
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

    public Integer getArticle() {
        return article;
    }

    public void setArticle(Integer article) {
        this.article = article;
    }
}