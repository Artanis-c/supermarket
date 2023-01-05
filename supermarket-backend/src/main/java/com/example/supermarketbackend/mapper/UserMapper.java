package com.example.supermarketbackend.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.supermarketbackend.model.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    IPage<User> list(IPage page, @Param("userName")String userName);
}
