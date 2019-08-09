package com.example.productdemo.entity.po;

public class SubTotalLine {
    private String id;
    private SubtotalLineDetail subtotalLineDetail;
    private String DetailType;
    private float amount;
    private String Description;
    private int LineNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SubtotalLineDetail getSubtotalLineDetail() {
        return subtotalLineDetail;
    }

    public void setSubtotalLineDetail(SubtotalLineDetail subtotalLineDetail) {
        this.subtotalLineDetail = subtotalLineDetail;
    }

    public String getDetailType() {
        return DetailType;
    }

    public void setDetailType(String detailType) {
        DetailType = detailType;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
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
