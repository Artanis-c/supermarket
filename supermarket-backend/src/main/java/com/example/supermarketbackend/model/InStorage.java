package com.example.supermarketbackend.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author tom.cui
 * @date 2023/1/6
 * @description 入库
 */
@Data
@TableName("in_storage")
public class InStorage {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer goodsId;

    private Integer number;

    private Integer userId;

    private LocalDateTime createTime;
}
