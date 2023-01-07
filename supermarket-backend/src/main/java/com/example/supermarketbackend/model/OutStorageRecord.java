package com.example.supermarketbackend.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author tom.cui
 * @date 2023/1/6
 * @description
 */
@Data
public class OutStorageRecord {
    private Integer id;

    private String goodsName;

    private String userName;

    private Integer number;

    private Integer goodsId;

    private Integer userId;

    private LocalDateTime createTime;

    private String remark;
}
