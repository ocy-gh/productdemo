package com.example.productdemo.entity.po;

public class SalesItemLine {

    private String Id;
    private String DetailType;
    private SalesItemLineDetail salesItemLineDetail;
    private float Amount;
    private String Description;
    private float LineNum;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public float getAmount() {
        return Amount;
    }

    public void setAmount(float amount) {
        Amount = amount;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public float getLineNum() {
        return LineNum;
    }

    public void setLineNum(float lineNum) {
        LineNum = lineNum;
    }

    public String getDetailType() {
        return DetailType;
    }

    public void setDetailType(String detailType) {
        DetailType = detailType;
    }

    public SalesItemLineDetail getSalesItemLineDetail() {
        return salesItemLineDetail;
    }

    public void setSalesItemLineDetail(SalesItemLineDetail salesItemLineDetail) {
        this.salesItemLineDetail = salesItemLineDetail;
    }
}
