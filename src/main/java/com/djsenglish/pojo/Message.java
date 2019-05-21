package com.djsenglish.pojo;

import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: shuo
 * @date: 2019/05/15
 */
public class Message implements Serializable {
    Integer senderId;
    Integer to;
    String text;
    DateTime time;

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public DateTime getTime() {
        return time;
    }

    public void setTime(DateTime time) {
        this.time = time;
    }

    public Message() {
    }

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }

    public Message(Integer senderId, Integer to, String text, DateTime time) {
        this.senderId = senderId;
        this.to = to;
        this.text = text;
        this.time = time;
    }
}
