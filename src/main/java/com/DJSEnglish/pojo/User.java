package com.DJSEnglish.pojo;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.Date;

public class User {
    private Integer id;

    private String name;

    @JsonIgnore
    private String password;

    private String sex;

    private String stage;

    private String msg;

    private String img;

    private String phone;


    @JsonIgnore
    private Date updateTime;

    @JsonIgnore
    private Date createTime;

    public User(Integer id, String name, String password, String sex, String stage , String msg, String img, String phone, Date updateTime, Date createTime) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.stage = stage;
        this.sex = sex;
        this.msg = msg;
        this.img = img;
        this.phone = phone;
        this.updateTime = updateTime;
        this.createTime = createTime;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public User() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg == null ? null : msg.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
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