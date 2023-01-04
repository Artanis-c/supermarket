package com.example.supermarketbackend.service;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.supermarketbackend.mapper.UserMapper;
import com.example.supermarketbackend.model.ResultModel;
import com.example.supermarketbackend.model.User;

/**
 * @author tom.cui
 * @date 2023/1/4
 * @description
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User> {

    private static final String USER_NAME = "USERNAME";
    private static final String USER_ID = "USERID";

    /**
     * @description: 登录
     * @author: tom.cui
     * @date: 2023/1/4
     */
    public ResultModel<Boolean> login(String userName, String password, HttpSession httpSession) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>();
        queryWrapper.eq(User::getName, userName);
        queryWrapper.eq(User::getPassword, password);
        User user = getOne(queryWrapper);
        if (Objects.nonNull(user)) {
            httpSession.setAttribute(USER_NAME, userName);
            httpSession.setAttribute(USER_ID, user.getId());
        }
        return new ResultModel<>(Objects.nonNull(user));
    }

    /**
     * @description: 查询当前用户
     * @author: tom.cui
     * @date: 2023/1/4
     */
    public ResultModel<User> queryCurrentUser(HttpSession httpSession) {
        Integer userid = (Integer)httpSession.getAttribute(USER_ID);
        User user = getById(userid);
        return new ResultModel<>(user);
    }

    /**
     * @description: 添加用户
     * @author: tom.cui
     * @date: 2023/1/4
     */
    public ResultModel<User> addUser(User model) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>();
        queryWrapper.eq(User::getName, model.getName());
        User one = getOne(queryWrapper);
        if (Objects.nonNull(one)) {
            return new ResultModel<>("用户名已存在", false);
        }
        boolean save = save(model);
        return new ResultModel<>(model);
    }

    public ResultModel<List<User>> list(String userName){
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>();
        if (StringUtils.isNotBlank(userName)){
            queryWrapper.eq(User::getName, userName);
        }
        List<User> list = list(queryWrapper);
        return new ResultModel<>(list);
    }
}
