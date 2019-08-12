package com.example.productdemo.service;

import com.example.productdemo.entity.po.StoreApiIntuit;

public interface IStoreApiIntuitService {

    StoreApiIntuit selectBySellerId(long seller_id);

    void insert(StoreApiIntuit storeApiIntuit);

    void updateByStoreApiIntuit(StoreApiIntuit storeApiIntuit);

    void deleteByStoreApiIntuit(StoreApiIntuit storeApiIntuit);
}
