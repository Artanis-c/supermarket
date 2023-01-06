package com.example.supermarketbackend.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * @author tom.cui
 * @date 2023/1/5
 * @description
 */
@Data
public class Goods {
    private Integer id;

    private String goodsName;

    private BigDecimal price;

    private Integer stock;

    private String remark;

    private LocalDateTime createTime;
}
