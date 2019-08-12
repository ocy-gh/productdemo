package com.example.productdemo.service.Impl;

import com.example.productdemo.entity.po.StoreApiIntuit;
import com.example.productdemo.mapper.StoreApiIntuitMapper;
import com.example.productdemo.service.IStoreApiIntuitService;
import org.springframework.stereotype.Service;

@Service
public class StoreApiIntuitImpl implements IStoreApiIntuitService {
    private final StoreApiIntuitMapper storeApiIntuitMapper;

    public StoreApiIntuitImpl(StoreApiIntuitMapper storeApiIntuitMapper) {
        this.storeApiIntuitMapper = storeApiIntuitMapper;
    }

    @Override
    public void insert(StoreApiIntuit storeApiIntuit) { storeApiIntuitMapper.insert(storeApiIntuit); }
}
