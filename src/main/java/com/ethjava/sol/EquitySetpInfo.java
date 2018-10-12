package com.ethjava.sol;

import java.math.BigInteger;

public class EquitySetpInfo {

    //行权id
    private BigInteger artworkId;
    //艺术家id
    private String artistId;
    //艺术家姓名
    private String artistName;
    //权益标题
    private String equityTitle;
    //步骤名称
    private String stepName;
    //步骤制做时间
    private String constTime;
    //上链保存时间
    private String createTime;
    //备注
    private String note;

    public BigInteger getArtworkId() {
        return artworkId;
    }

    public void setArtworkId(BigInteger artworkId) {
        this.artworkId = artworkId;
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

    public String getEquityTitle() {
        return equityTitle;
    }

    public void setEquityTitle(String equityTitle) {
        this.equityTitle = equityTitle;
    }

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public String getConstTime() {
        return constTime;
    }

    public void setConstTime(String constTime) {
        this.constTime = constTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


}
