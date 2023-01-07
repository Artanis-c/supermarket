package com.example.supermarketbackend.model;

import lombok.Data;

/**
 * @author tom.cui
 * @date 2023/1/6
 * @description
 */
@Data
public class KeyValuePairModel {

    public KeyValuePairModel(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

    private Integer value;

    private String label;
}
