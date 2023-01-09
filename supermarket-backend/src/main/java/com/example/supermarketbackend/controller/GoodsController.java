package com.example.supermarketbackend.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.supermarketbackend.model.Goods;
import com.example.supermarketbackend.model.KeyValuePairModel;
import com.example.supermarketbackend.model.ResultModel;
import com.example.supermarketbackend.req.GoodsRequest;
import com.example.supermarketbackend.service.GoodsService;

/**
 * @author tom.cui
 * @date 2023/1/6
 * @description
 */
@RestController
@RequestMapping("/api/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @PostMapping("/list")
    public ResultModel<IPage<Goods>> queryGoods(@RequestBody GoodsRequest goodsRequest) {
        return goodsService.queryGoods(goodsRequest);
    }

    @GetMapping("/keyValue")
    public ResultModel<List<KeyValuePairModel>> getGoodsKeyValuePair() {
        List<Goods> list = goodsService.list();
        List<KeyValuePairModel> collect =
            list.stream().map(x -> new KeyValuePairModel(x.getId(), x.getGoodsName())).collect(Collectors.toList());
        return ResultModel.of(collect);
    }

    @PostMapping("/add")
    public ResultModel<Goods> addGoods(@RequestBody Goods goods) {
        return goodsService.addGoods(goods);
    }

    @PostMapping("/edit")
    public ResultModel<Goods> editGoods(@RequestBody Goods goods) {
        return goodsService.editGoods(goods);
    }

    @GetMapping("/remove/{id}")
    public ResultModel<Integer> remove(@PathVariable("id") Integer id) {
        return goodsService.deleteGoods(id);
    }

    @GetMapping("/get/{id}")
    public ResultModel<Integer> getGoods(@PathVariable("id") Integer id) {
        Goods byId = goodsService.getById(id);
        return ResultModel.of(byId);
    }


}
