package com.example.productdemo.entity.po;

public class MarkupInfo {

    private MarkkupAccountRef markkupAccountRef;
    private PriceLevelRef priceLevelRef;
    private float Percent;

    public float getPercent() {
        return Percent;
    }

    public void setPercent(float percent) {
        Percent = percent;
    }

    public MarkkupAccountRef getMarkkupAccountRef() {
        return markkupAccountRef;
    }

    public void setMarkkupAccountRef(MarkkupAccountRef markkupAccountRef) {
        this.markkupAccountRef = markkupAccountRef;
    }

    public PriceLevelRef getPriceLevelRef() {
        return priceLevelRef;
    }

    public void setPriceLevelRef(PriceLevelRef priceLevelRef) {
        this.priceLevelRef = priceLevelRef;
    }
}
