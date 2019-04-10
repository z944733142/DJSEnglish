package com.DJSEnglish.pojo;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.Date;

public class User {
    private Integer id;

    private String name;

    private String username;

    @JsonIgnore
    private String password;

    private String msg;

    private String img;

    private String email;

    private String phone;


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", msg='" + msg + '\'' +
                ", img='" + img + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                '}';
    }

    @JsonIgnore
    private Date updateTime;

    @JsonIgnore
    private Date createTime;

    public User(Integer id, String name, String username, String password, String msg, String img, String email, String phone, Date updateTime, Date createTime) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.msg = msg;
        this.img = img;
        this.email = email;
        this.phone = phone;
        this.updateTime = updateTime;
        this.createTime = createTime;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
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