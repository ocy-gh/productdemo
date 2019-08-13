package com.example.productdemo.controller;

import com.example.productdemo.entity.po.Customer;
import com.example.productdemo.service.Impl.CustomerServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerListController {
    private final CustomerServiceImpl customerService;

    public CustomerListController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/list_customer")
    public String customer(Model model) {
        // TODO: Display a list of customer from intuit QBO API
        return "customer";
    }

    @GetMapping("/bind")
    public String customerBind(Model model) {
        String customerId = "1";
        String customerName = "Shopee";

        Customer customer = new Customer(123L,"S258","shopee","");
        customerService.insertCustomer(customer);
        model.addAttribute("Customer",customer);
        return "customer";
    }
}
