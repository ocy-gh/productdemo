package com.example.productdemo.mapper;

import com.example.productdemo.entity.po.Customer;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerMapper {
    void insertCustomer(Customer customer);
}
