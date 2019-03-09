package com.DJSEnglish.pojo;

import java.util.Date;

public class ChattingRecords {
    private Integer id;

    private Integer sender;

    private Integer receiver;

    private Date updateTime;

    private Date createTime;

    public ChattingRecords(Integer id, Integer sender, Integer receiver, Date updateTime, Date createTime) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.updateTime = updateTime;
        this.createTime = createTime;
    }

    public ChattingRecords() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSender() {
        return sender;
    }

    public void setSender(Integer sender) {
        this.sender = sender;
    }

    public Integer getReceiver() {
        return receiver;
    }

    public void setReceiver(Integer receiver) {
        this.receiver = receiver;
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