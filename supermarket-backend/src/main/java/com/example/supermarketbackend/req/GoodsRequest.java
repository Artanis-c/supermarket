package com.example.supermarketbackend.req;

import lombok.Data;

/**
 * @author tom.cui
 * @date 2023/1/5
 * @description
 */
@Data
public class GoodsRequest extends  PageRequest{

    private String goodsName;
}
