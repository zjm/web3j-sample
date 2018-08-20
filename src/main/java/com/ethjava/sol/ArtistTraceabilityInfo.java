package com.ethjava.sol;

import java.math.BigInteger;

public class ArtistTraceabilityInfo {
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
