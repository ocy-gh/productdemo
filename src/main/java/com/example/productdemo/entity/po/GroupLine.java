package com.example.productdemo.entity.po;

public class GroupLine {
    private String id;
    private GroupLineDetails groupLineDetails;
    private String DetailType;
    private int LineNum;
    private String Description;

    public GroupLineDetails getGroupLineDetails() {
        return groupLineDetails;
    }

    public void setGroupLineDetails(GroupLineDetails groupLineDetails) {
        this.groupLineDetails = groupLineDetails;
    }

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

    public int getLineNum() {
        return LineNum;
    }

    public void setLineNum(int lineNum) {
        LineNum = lineNum;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
