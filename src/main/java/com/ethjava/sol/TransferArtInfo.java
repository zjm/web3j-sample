package com.ethjava.sol;

public class TransferArtInfo {

    //卖家会员id
    private String sellerMemberId;
    //卖家
    private String seller;
    //买家会员id
    private String buyerMemberId;
    //买家
    private String buyer;
    //交易时间
    private String transTime;//timestamp
    //备注信息
    private String note;

    public String getSellerMemberId() {
        return sellerMemberId;
    }

    public void setSellerMemberId(String sellerMemberId) {
        this.sellerMemberId = sellerMemberId;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getBuyerMemberId() {
        return buyerMemberId;
    }

    public void setBuyerMemberId(String buyerMemberId) {
        this.buyerMemberId = buyerMemberId;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getTransTime() {
        return transTime;
    }

    public void setTransTime(String transTime) {
        this.transTime = transTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


}
