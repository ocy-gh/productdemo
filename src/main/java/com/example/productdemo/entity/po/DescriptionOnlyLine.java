package com.example.productdemo.entity.po;

public class DescriptionOnlyLine {
    private String id;
    private String DetailType;
    private DescriptionLineDetails descriptionLineDetails;
    private String description;
    private int LineNum;
    private float amount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DescriptionLineDetails getDescriptionLineDetails() {
        return descriptionLineDetails;
    }

    public void setDescriptionLineDetails(DescriptionLineDetails descriptionLineDetails) {
        this.descriptionLineDetails = descriptionLineDetails;
    }

    public String getDetailType() {
        return DetailType;
    }

    public void setDetailType(String detailType) {
        DetailType = detailType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLineNum() {
        return LineNum;
    }

    public void setLineNum(int lineNum) {
        LineNum = lineNum;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
