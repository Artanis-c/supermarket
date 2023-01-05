package com.example.supermarketbackend.model;

import lombok.Data;

/**
 * @author tom.cui
 * @date 2023/1/4
 * @description
 */
@Data
public class ResultModel<T> {

    private boolean success;

    private T data;

    private String errorCode;

    private String errorMessage;

    private int showType;

    private String traceId;

    public ResultModel(T data) {
        this.success = true;
        this.data = data;
    }

    public ResultModel(T data, boolean success) {
        this.data = data;
        this.success = success;
    }

    public ResultModel(String msg, boolean success) {
        this.errorMessage = msg;
        this.success = success;
    }

    public static ResultModel of(Object data){
        return new ResultModel(data);
    }

    public static ResultModel fail(String msg){
        return new ResultModel(msg,false);
    }
}
