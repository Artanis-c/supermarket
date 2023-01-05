package com.example.supermarketbackend.req;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * @author tom.cui
 * @date 2023/1/5
 * @description
 */
@Data
public class PageRequest {

    private Integer current;

    private Integer pageSize;


    public Page genPage(){
        Page page=new Page(current,pageSize);
        return page;
    }
}
