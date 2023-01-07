package com.example.supermarketbackend.req;

import lombok.Data;

/**
 * @author tom.cui
 * @date 2023/1/6
 * @description
 */
@Data
public class OutStorageRequest extends PageRequest{
    private String userName;

    private String  goodsName;

}
