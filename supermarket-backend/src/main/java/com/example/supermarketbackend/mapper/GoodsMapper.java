package com.example.supermarketbackend.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.supermarketbackend.model.Goods;

/**
 * @author tom.cui
 * @date 2023/1/5
 * @description
 */
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {

    public IPage<Goods> queryGoods(IPage page, @Param("goodsName") String goodsName);

}
