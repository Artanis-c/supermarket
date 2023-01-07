package com.example.supermarketbackend.controller;

import static com.example.supermarketbackend.service.UserService.USER_ID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.supermarketbackend.model.InStorage;
import com.example.supermarketbackend.model.InStorageRecord;
import com.example.supermarketbackend.model.ResultModel;
import com.example.supermarketbackend.req.InStorageRequest;
import com.example.supermarketbackend.service.InStorageService;

/**
 * @author tom.cui
 * @date 2023/1/6
 * @description
 */
@RestController
@RequestMapping("/api/in-storage")
public class InStorageController {

    @Autowired
    private InStorageService inStorageService;

    @PostMapping("/list")
    public ResultModel<Page<InStorageRecord>> queryInStorage(@RequestBody InStorageRequest req) {
        return inStorageService.queryInStorage(req);
    }

    @PostMapping("/add")
    public ResultModel<InStorage> add(HttpSession session, @RequestBody InStorage req) {
        Integer userId = (Integer)session.getAttribute(USER_ID);
        req.setUserId(userId);
        return inStorageService.add(req);
    }

    @GetMapping("/get/{id}")
    public ResultModel<InStorageRecord> getRecord(@PathVariable("id") Integer id) {
        return inStorageService.getRecord(id);
    }
}
