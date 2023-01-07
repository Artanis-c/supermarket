package com.example.supermarketbackend.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.supermarketbackend.mapper.GoodsMapper;
import com.example.supermarketbackend.mapper.InStorageMapper;
import com.example.supermarketbackend.model.Goods;
import com.example.supermarketbackend.model.InStorage;
import com.example.supermarketbackend.model.InStorageRecord;
import com.example.supermarketbackend.model.ResultModel;
import com.example.supermarketbackend.req.InStorageRequest;

/**
 * @author tom.cui
 * @date 2023/1/6
 * @description
 */
@Service
public class InStorageService extends ServiceImpl<InStorageMapper, InStorage> {

    @Autowired
    private GoodsMapper goodsMapper;

    public ResultModel<Page<InStorageRecord>> queryInStorage(InStorageRequest req) {
        IPage<InStorageRecord> inStorageRecordIPage = baseMapper.queryInStorageRecord(req.genPage(), req);
        return ResultModel.of(inStorageRecordIPage);
    }

    @Transactional(rollbackFor = Exception.class)
    public ResultModel<InStorage> add(InStorage req) {
        Goods goods = goodsMapper.selectById(req.getGoodsId());
        Integer newStock = goods.getStock() + req.getNumber();
        goods.setStock(newStock);
        goodsMapper.updateById(goods);
        boolean saveRes = save(req);
        if (saveRes) {
            return ResultModel.of(req);
        } else {
            return ResultModel.fail("保存入库记录失败");
        }
    }

    public ResultModel<InStorageRecord> getRecord(Integer id) {
        InStorageRecord inStorageRecord = baseMapper.getInStorageRecord(id);
        if (Objects.isNull(inStorageRecord)) {
            return ResultModel.fail("没有找到对应的记录");
        }
        return ResultModel.of(inStorageRecord);
    }
}
