package com.example.supermarketbackend.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.supermarketbackend.model.ResultModel;
import com.example.supermarketbackend.model.User;
import com.example.supermarketbackend.req.UserRequest;
import com.example.supermarketbackend.service.UserService;

/**
 * @author tom.cui
 * @date 2023/1/4
 * @description
 */

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public ResultModel<Boolean> login(@RequestParam("userName") String userName,
        @RequestParam("password") String password, HttpSession httpSession) {
        ResultModel<Boolean> loginRes = userService.login(userName, password, httpSession);
        return loginRes;
    }

    @GetMapping("/currentUser")
    public ResultModel<User> queryCurrentUser(HttpSession httpSession) {
        return userService.queryCurrentUser(httpSession);
    }

    @PostMapping("/add")
    public ResultModel<User> addUser(@RequestBody User model) {
        return userService.addUser(model);
    }

    @PostMapping("/list")
    public ResultModel<IPage<User>> list(@RequestBody UserRequest request) {
        return userService.list(request);
    }

    @GetMapping("/get/{id}")
    public ResultModel<User> queryUser(@PathVariable("id") Integer id) {
        return userService.queryUser(id);
    }

    @PostMapping("/edit")
    public ResultModel<User> editUser(@RequestBody User req) {
        return userService.editUser(req);
    }
}
