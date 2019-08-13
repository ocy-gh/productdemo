package com.example.productdemo.entity.po;

public class Customer {
    private long seller_id;
    private String customer_id;
    private String customer_name;
    private String created_at;

    public Customer(long seller_id, String customer_id, String customer_name, String created_at) {
        this.seller_id = seller_id;
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.created_at = created_at;
    }

    public long getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(long seller_id) {
        this.seller_id = seller_id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "seller_id=" + seller_id +
                ", customer_id='" + customer_id + '\'' +
                ", customer_name='" + customer_name + '\'' +
                ", created_at='" + created_at + '\'' +
                '}';
    }
}
