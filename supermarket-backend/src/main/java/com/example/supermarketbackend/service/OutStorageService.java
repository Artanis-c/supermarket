package com.example.supermarketbackend.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.supermarketbackend.mapper.GoodsMapper;
import com.example.supermarketbackend.model.*;
import com.example.supermarketbackend.req.OutStorageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.supermarketbackend.mapper.OutStorageMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * @author tom.cui
 * @date 2023/1/6
 * @description
 */
@Service
public class OutStorageService extends ServiceImpl<OutStorageMapper, OutStorage> {
    @Autowired
    private GoodsMapper goodsMapper;

    public ResultModel<Page<OutStorageRecord>> queryOutStorage(OutStorageRequest req) {
        IPage<OutStorageRecord> OutStorageRecordIPage = baseMapper.queryOutStorageRecord(req.genPage(), req);
        return ResultModel.of(OutStorageRecordIPage);
    }

    @Transactional(rollbackFor = Exception.class)
    public ResultModel<OutStorage> add(OutStorage req) {
        Goods goods = goodsMapper.selectById(req.getGoodsId());
        Integer newStock = goods.getStock() - req.getNumber();
        goods.setStock(newStock);
        goodsMapper.updateById(goods);
        boolean saveRes = save(req);
        if (saveRes) {
            return ResultModel.of(req);
        } else {
            return ResultModel.fail("保存出库记录失败");
        }
    }

    public ResultModel<OutStorageRecord> getRecord(Integer id) {
        OutStorageRecord OutStorageRecord = baseMapper.getOutStorageRecord(id);
        if (Objects.isNull(OutStorageRecord)) {
            return ResultModel.fail("没有找到对应的记录");
        }
        return ResultModel.of(OutStorageRecord);
    }
}
