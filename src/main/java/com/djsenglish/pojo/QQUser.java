package com.djsenglish.pojo;

import java.util.Date;

public class QQUser {
    private Integer id;

    private String qqId;

    private String phone;

    private Date updateTime;

    private Date createTime;

    public QQUser(Integer id, String qqId, String phone, Date updateTime, Date createTime) {
        this.id = id;
        this.qqId = qqId;
        this.phone = phone;
        this.updateTime = updateTime;
        this.createTime = createTime;
    }

    public QQUser() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQqId() {
        return qqId;
    }

    public void setQqId(String qqId) {
        this.qqId = qqId == null ? null : qqId.trim();
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