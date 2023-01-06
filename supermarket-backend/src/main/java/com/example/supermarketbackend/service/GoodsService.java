package com.example.supermarketbackend.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.supermarketbackend.mapper.GoodsMapper;
import com.example.supermarketbackend.model.Goods;
import com.example.supermarketbackend.model.ResultModel;
import com.example.supermarketbackend.req.GoodsRequest;
import org.springframework.stereotype.Service;

/**
 * @author tom.cui
 * @date 2023/1/5
 * @description
 */
@Service
public class GoodsService extends ServiceImpl<GoodsMapper, Goods> {

    public ResultModel<IPage<Goods>> queryGoods(GoodsRequest goodsRequest) {
        IPage<Goods> goodsIPage = baseMapper.queryGoods(goodsRequest.genPage(), goodsRequest.getGoodsName());
        return ResultModel.of(goodsIPage);
    }

    public ResultModel<Goods> addGoods(Goods goods) {
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

}
