package com.djsenglish.vo;

import java.util.Date;

/**
 * @author: shuo
 * @date: 2019/05/26
 */
public class MessageVo {
    private String sender;
    private String text;
    private Date time;

    public MessageVo() {
    }

    public MessageVo(String sender, String text, Date time) {
        this.sender = sender;
        this.text = text;
        this.time = time;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
