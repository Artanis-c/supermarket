package com.example.supermarketbackend.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author tom.cui
 * @date 2023/1/6
 * @description
 */
@Data
public class InStorageRecord {

    private Integer id;

    private String goodName;

    private String userName;

    private Integer number;

    private LocalDateTime createTime;
}
