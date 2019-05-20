package com.djsenglish.pojo;

public class QQUser {
    private Integer id;

    private String qqId;

    private String phone;

    public QQUser(Integer id, String qqId, String phone) {
        this.id = id;
        this.qqId = qqId;
        this.phone = phone;
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
}