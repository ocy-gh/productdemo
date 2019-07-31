package com.example.productdemo.entity.po;

public class SalesItemLineDetail {

    private float TaxInclusiveAmt;
    private float DiscountAmt;
    private ItemRef itemRef;
    private ClassRef classRef;
    private TaxCodeRef taxCodeRef;
    private MarkupInfo markupInfo;
    private ItemAccountRef itemAccountRef;
    private ServiceDate serviceDate;
    private float DiscountRate;
    private float Qty;
    private float UnitPrice;
    private TaxClassificationRef taxClassificationRef;

    public float getTaxInclusiveAmt() {
        return TaxInclusiveAmt;
    }

    public void setTaxInclusiveAmt(float taxInclusiveAmt) {
        TaxInclusiveAmt = taxInclusiveAmt;
    }

    public float getDiscountAmt() {
        return DiscountAmt;
    }

    public void setDiscountAmt(float discountAmt) {
        DiscountAmt = discountAmt;
    }

    public float getDiscountRate() {
        return DiscountRate;
    }

    public void setDiscountRate(float discountRate) {
        DiscountRate = discountRate;
    }

    public float getQty() {
        return Qty;
    }

    public void setQty(float qty) {
        Qty = qty;
    }

    public float getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        UnitPrice = unitPrice;
    }

    public ItemRef getItemRef() {
        return itemRef;
    }

    public void setItemRef(ItemRef itemRef) {
        this.itemRef = itemRef;
    }

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

    public MarkupInfo getMarkupInfo() {
        return markupInfo;
    }

    public void setMarkupInfo(MarkupInfo markupInfo) {
        this.markupInfo = markupInfo;
    }

    public ItemAccountRef getItemAccountRef() {
        return itemAccountRef;
    }

    public void setItemAccountRef(ItemAccountRef itemAccountRef) {
        this.itemAccountRef = itemAccountRef;
    }

    public ServiceDate getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(ServiceDate serviceDate) {
        this.serviceDate = serviceDate;
    }

    public TaxClassificationRef getTaxClassificationRef() {
        return taxClassificationRef;
    }

    public void setTaxClassificationRef(TaxClassificationRef taxClassificationRef) {
        this.taxClassificationRef = taxClassificationRef;
    }
}
