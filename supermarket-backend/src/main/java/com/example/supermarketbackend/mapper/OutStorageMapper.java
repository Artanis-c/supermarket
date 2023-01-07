package com.example.supermarketbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.supermarketbackend.model.OutStorage;
import com.example.supermarketbackend.model.OutStorageRecord;
import com.example.supermarketbackend.req.OutStorageRequest;

/**
 * @author tom.cui
 * @date 2023/1/6
 * @description
 */
public interface OutStorageMapper extends BaseMapper<OutStorage> {

    IPage<OutStorageRecord> queryOutStorageRecord(IPage page, OutStorageRequest inStorageRequest);

    OutStorageRecord getOutStorageRecord(Integer id);
}
