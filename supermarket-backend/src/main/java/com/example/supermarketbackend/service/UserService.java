package com.example.supermarketbackend.service;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.supermarketbackend.mapper.UserMapper;
import com.example.supermarketbackend.model.ResultModel;
import com.example.supermarketbackend.model.User;
import com.example.supermarketbackend.req.UserRequest;

/**
 * @author tom.cui
 * @date 2023/1/4
 * @description
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User> {

    public static final String USER_NAME = "USERNAME";
    public static final String USER_ID = "USERID";

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
        model.setCreateTime(LocalDateTime.now());
        boolean save = save(model);
        return new ResultModel<>(model);
    }

    public ResultModel<IPage<User>> list(UserRequest request) {
        IPage<User> list = baseMapper.list(request.genPage(), request.getName());
        return new ResultModel<>(list);
    }

    public ResultModel<User> queryUser(Integer id) {
        User user = getById(id);
        if (Objects.nonNull(user)) {
            return new ResultModel<>(user);
        } else {
            return new ResultModel<>("没有找到用户", false);
        }

    }

    public ResultModel<User> editUser(User req) {
        User user = getById(req.getId());
        if (Objects.isNull(user)) {
            return ResultModel.fail("用户不存在");
        }
        updateById(req);
        return ResultModel.of(req);
    }
}
