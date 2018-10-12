package com.ethjava.sol;

import java.math.BigInteger;

public class ArtistTraceabilityInfo {
    //艺术品版本号
    private String version;
    //艺术家Id
    private String artistId;
    //艺术家姓名
    private String artistName;
    //艺术创建日期
    private String data;
    //备注信息
    private String note;
    //链上保存日期
    private String createTime;
    //艺术品名称
    private String artTitle;
    //技法
    private String tecSkill;
    //规格:有的是长宽高衡量;有的是重量衡量...
    private String  artSpecification;

    public String getArtTitle() {
        return artTitle;
    }

    public void setArtTitle(String artTitle) {
        this.artTitle = artTitle;
    }

    public String getTecSkill() {
        return tecSkill;
    }

    public void setTecSkill(String tecSkill) {
        this.tecSkill = tecSkill;
    }

    public String getArtSpecification() {
        return artSpecification;
    }

    public void setArtSpecification(String artSpecification) {
        this.artSpecification = artSpecification;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }


    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }



}
