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
    public StoreApiIntuit selectBySellerId(long seller_id) {
        return storeApiIntuitMapper.selectBySellerId(seller_id);
    }

    @Override
    public void insert(StoreApiIntuit storeApiIntuit) { storeApiIntuitMapper.insert(storeApiIntuit); }

    @Override
    public void updateByStoreApiIntuit(StoreApiIntuit storeApiIntuit) {
        storeApiIntuitMapper.updateByStoreApiIntuit(storeApiIntuit);
    }

    @Override
    public void deleteByStoreApiIntuit(StoreApiIntuit storeApiIntuit) {
        storeApiIntuitMapper.deleteByStoreApiIntuit(storeApiIntuit);
    }
}
