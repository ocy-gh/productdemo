package com.example.productdemo.entity.po;

import com.example.productdemo.entity.po.TaxCodeRef;

import java.util.Date;

public class DescriptionLineDetails {
    private TaxCodeRef taxCodeRef;
    private Date ServiceDate;

    public TaxCodeRef getTaxCodeRef() {
        return taxCodeRef;
    }

    public void setTaxCodeRef(TaxCodeRef taxCodeRef) {
        this.taxCodeRef = taxCodeRef;
    }

    public Date getServiceDate() {
        return ServiceDate;
    }

    public void setServiceDate(Date serviceDate) {
        ServiceDate = serviceDate;
    }
}
