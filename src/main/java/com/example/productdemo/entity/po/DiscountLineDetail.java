package com.example.productdemo.entity.po;

public class DiscountLineDetail {
    private ClassRef classRef;
    private TaxCodeRef taxCodeRef;
    private DiscountAccountRef discountAccountRef;
    private Boolean PercentBased;
    private int DiscountPercent;

    public ClassRef getClassRef() {
        return classRef;
    }

    public void setClassRef(ClassRef classRef) {
        this.classRef = classRef;
    }

    public TaxCodeRef getTaxCodeRef() {
        return taxCodeRef;
    }

    public void setTaxCodeRef(TaxCodeRef taxCodeRef) {
        this.taxCodeRef = taxCodeRef;
    }

    public DiscountAccountRef getDiscountAccountRef() {
        return discountAccountRef;
    }

    public void setDiscountAccountRef(DiscountAccountRef discountAccountRef) {
        this.discountAccountRef = discountAccountRef;
    }

    public Boolean getPercentBased() {
        return PercentBased;
    }

    public void setPercentBased(Boolean percentBased) {
        PercentBased = percentBased;
    }

    public int getDiscountPercent() {
        return DiscountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        DiscountPercent = discountPercent;
    }
}
