package com.example.supermarketbackend.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.supermarketbackend.mapper.GoodsMapper;
import com.example.supermarketbackend.model.*;
import com.example.supermarketbackend.req.GoodsRequest;

/**
 * @author tom.cui
 * @date 2023/1/5
 * @description
 */
@Service
public class GoodsService extends ServiceImpl<GoodsMapper, Goods> {

    @Autowired
    private InStorageService inStorageService;

    @Autowired
    private OutStorageService outStorageService;

    @Autowired
    private GoodsMapper goodsMapper;

    public ResultModel<IPage<Goods>> queryGoods(GoodsRequest goodsRequest) {
        IPage<Goods> goodsIPage = baseMapper.queryGoods(goodsRequest.genPage(), goodsRequest.getGoodsName());
        return ResultModel.of(goodsIPage);
    }

    public ResultModel<Goods> addGoods(Goods goods) {
        goods.setCreateTime(LocalDateTime.now());
        save(goods);
        return ResultModel.of(goods);
    }

    public ResultModel<Goods> editGoods(Goods goods) {
        boolean res = updateById(goods);
        if (res) {
            return ResultModel.of(goods);
        } else {
            return ResultModel.fail("更新商品失败");
        }
    }

    public ResultModel<Integer> deleteGoods(Integer id) {
        boolean remove = removeById(id);
        if (remove) {
            return ResultModel.of(id);
        } else {
            return ResultModel.fail("删除商品失败");
        }
    }

    public ResultModel<List<StatisticRes>> statistic() {
        List<StatisticRes> resList = new ArrayList<>();
        List<InStorage> inStorageList = inStorageService.list();
        List<OutStorage> outStorageList = outStorageService.list();
        for (InStorage inStorage : inStorageList) {
            Optional<StatisticRes> first =
                resList.stream().filter(x -> x.getGoodsId().equals(inStorage.getGoodsId())).findFirst();
            if (first.isPresent()) {
                StatisticRes statisticRes = first.get();
                statisticRes.setInNum(statisticRes.getInNum() + inStorage.getNumber());
            } else {
                StatisticRes statisticRes = new StatisticRes();
                statisticRes.setGoodsId(inStorage.getGoodsId());
                statisticRes.setGoodsName(goodsMapper.selectById(inStorage.getGoodsId()).getGoodsName());
                statisticRes.setInNum(inStorage.getNumber());
                resList.add(statisticRes);
            }
        }
        for (OutStorage outStorage : outStorageList) {
            Optional<StatisticRes> first =
                    resList.stream().filter(x -> x.getGoodsId().equals(outStorage.getGoodsId())).findFirst();
            if (first.isPresent()) {
                StatisticRes statisticRes = first.get();
                statisticRes.setInNum(statisticRes.getInNum() + outStorage.getNumber());
            } else {
                StatisticRes statisticRes = new StatisticRes();
                statisticRes.setGoodsId(outStorage.getGoodsId());
                statisticRes.setGoodsName(goodsMapper.selectById(outStorage.getGoodsId()).getGoodsName());
                statisticRes.setOutNum(outStorage.getNumber());
                resList.add(statisticRes);
            }
        }
        return ResultModel.of(resList);
    }

}
