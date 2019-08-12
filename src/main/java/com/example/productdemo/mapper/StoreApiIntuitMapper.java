package com.example.productdemo.mapper;

import com.example.productdemo.entity.po.StoreApiIntuit;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreApiIntuitMapper {

    void insert(StoreApiIntuit storeApiIntuit);
}
