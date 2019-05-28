package com.djsenglish.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: shuo
 * @date: 2019/05/15
 */
public class Message implements Serializable {
    private Integer id;
    private Integer sender;
    private Integer to;
    private String text;
    @JsonIgnore
    private Date updateTime;
    private Date createTime;

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", sender=" + sender +
                ", to=" + to +
                ", text='" + text + '\'' +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                '}';
    }


    public Message(Integer sender, Integer to, String text) {
        this.sender = sender;
        this.to = to;
        this.text = text;
        this.updateTime = new Date();
        this.createTime = new Date();
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

    public Message(Integer id, Integer sender, Integer to, String text, Date updateTime, Date createTime) {
        this.id = id;
        this.sender = sender;
        this.to = to;
        this.text = text;
        this.updateTime = updateTime;
        this.createTime = createTime;
    }




    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }



    public Message() {
    }

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }




}
