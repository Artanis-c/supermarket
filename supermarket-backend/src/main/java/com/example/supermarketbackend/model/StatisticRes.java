package com.example.supermarketbackend.model;

import lombok.Data;

/**
 * @author tom.cui
 * @date 2023/1/29
 * @description
 */
@Data
public class StatisticRes {

    private Integer goodsId;

    private String goodsName;

    private Integer inNum;

    private Integer outNum;
}
