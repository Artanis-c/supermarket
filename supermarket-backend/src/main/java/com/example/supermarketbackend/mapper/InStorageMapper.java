package com.example.supermarketbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.supermarketbackend.model.InStorage;
import com.example.supermarketbackend.model.InStorageRecord;
import com.example.supermarketbackend.req.InStorageRequest;

/**
 * @author tom.cui
 * @date 2023/1/6
 * @description
 */
public interface InStorageMapper extends BaseMapper<InStorage> {


    IPage<InStorageRecord> queryInStorageRecord(IPage page, InStorageRequest inStorageRequest);

    InStorageRecord getInStorageRecord(Integer id);
}
