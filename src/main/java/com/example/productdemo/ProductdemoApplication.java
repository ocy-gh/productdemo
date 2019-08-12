package com.example.productdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.productdemo.mapper")
@SpringBootApplication
public class ProductdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductdemoApplication.class, args);
    }

}
