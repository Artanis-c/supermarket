package com.example.supermarketbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.supermarketbackend.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
