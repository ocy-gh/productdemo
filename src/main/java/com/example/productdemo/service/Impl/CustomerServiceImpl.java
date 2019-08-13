package com.example.productdemo.service.Impl;

import com.example.productdemo.entity.po.Customer;
import com.example.productdemo.mapper.CustomerMapper;
import com.example.productdemo.service.ICustomerService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements ICustomerService {
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    @Override
    public void insertCustomer(Customer customer) { customerMapper.insertCustomer(customer); }
}
