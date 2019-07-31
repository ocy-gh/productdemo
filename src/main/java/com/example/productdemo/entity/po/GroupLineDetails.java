package com.example.productdemo.entity.po;

public class GroupLineDetails {
    private int Quantity;
    private Line line;
    private GroupItemRef groupItemRef;

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public GroupItemRef getGroupItemRef() {
        return groupItemRef;
    }

    public void setGroupItemRef(GroupItemRef groupItemRef) {
        this.groupItemRef = groupItemRef;
    }
}
