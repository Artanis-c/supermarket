package com.example.supermarketbackend.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

/**
 * @author tom.cui
 * @date 2023/1/5
 * @description
 */
@Data
public class Goods {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String goodsName;

    private BigDecimal price;

    private Integer stock;

    private String remark;

    private LocalDateTime createTime;
}
