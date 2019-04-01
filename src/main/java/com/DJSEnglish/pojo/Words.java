package com.DJSEnglish.pojo;

import java.util.Date;

public class Words {
    private Integer id;

    private String word;

    private String soundMark;

    private String pos;

    private Date updateTime;

    private Date createTime;

    private String mean;

    public Words(Integer id, String word, String soundMark, String pos, Date updateTime, Date createTime, String mean) {
        this.id = id;
        this.word = word;
        this.soundMark = soundMark;
        this.pos = pos;
        this.updateTime = updateTime;
        this.createTime = createTime;
        this.mean = mean;
    }

    public Words() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word == null ? null : word.trim();
    }

    public String getSoundMark() {
        return soundMark;
    }

    public void setSoundMark(String soundMark) {
        this.soundMark = soundMark == null ? null : soundMark.trim();
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos == null ? null : pos.trim();
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

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean == null ? null : mean.trim();
    }
}