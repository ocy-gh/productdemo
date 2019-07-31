package com.example.productdemo.entity.po;

public class Line {

    private String id;
    private String DetailType;
    private SalesItemLineDetail salesItemLineDetail;
    private float amount;
    private String description;
    private int LineNum;
    private SalesItemLine salesItemLine;
    private GroupLine groupLine;
    private DescriptionOnlyLine descriptionOnlyLine;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
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

    public SalesItemLine getSalesItemLine() {
        return salesItemLine;
    }

    public void setSalesItemLine(SalesItemLine salesItemLine) {
        this.salesItemLine = salesItemLine;
    }

    public GroupLine getGroupLine() {
        return groupLine;
    }

    public void setGroupLine(GroupLine groupLine) {
        this.groupLine = groupLine;
    }

    public DescriptionOnlyLine getDescriptionOnlyLine() {
        return descriptionOnlyLine;
    }

    public void setDescriptionOnlyLine(DescriptionOnlyLine descriptionOnlyLine) {
        this.descriptionOnlyLine = descriptionOnlyLine;
    }
}
