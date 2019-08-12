package com.example.productdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.example.productdemo.mapper")
@EnableScheduling
public class ProductdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductdemoApplication.class, args);
    }

}
