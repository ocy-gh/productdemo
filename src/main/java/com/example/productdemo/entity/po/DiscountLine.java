package com.example.productdemo.entity.po;

public class DiscountLine {
    private String id;
    private DiscountLineDetail discountLineDetail;
    private String DetailType;
    private int Amount;
    private String Description;
    private int LineNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DiscountLineDetail getDiscountLineDetail() {
        return discountLineDetail;
    }

    public void setDiscountLineDetail(DiscountLineDetail discountLineDetail) {
        this.discountLineDetail = discountLineDetail;
    }

    public String getDetailType() {
        return DetailType;
    }

    public void setDetailType(String detailType) {
        DetailType = detailType;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getLineNum() {
        return LineNum;
    }

    public void setLineNum(int lineNum) {
        LineNum = lineNum;
    }
}
