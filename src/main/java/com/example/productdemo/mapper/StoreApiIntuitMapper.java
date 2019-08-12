package com.example.productdemo.mapper;

import com.example.productdemo.entity.po.StoreApiIntuit;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreApiIntuitMapper {

    StoreApiIntuit selectBySellerId(long seller_id);

    void insert(StoreApiIntuit storeApiIntuit);

    void updateByStoreApiIntuit(StoreApiIntuit storeApiIntuit);

    void deleteByStoreApiIntuit(StoreApiIntuit storeApiIntuit);
}
