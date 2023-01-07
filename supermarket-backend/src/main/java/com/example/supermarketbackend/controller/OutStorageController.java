package com.example.supermarketbackend.controller;

import static com.example.supermarketbackend.service.UserService.USER_ID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.supermarketbackend.model.OutStorage;
import com.example.supermarketbackend.model.OutStorageRecord;
import com.example.supermarketbackend.model.ResultModel;
import com.example.supermarketbackend.req.OutStorageRequest;
import com.example.supermarketbackend.service.OutStorageService;

/**
 * @author tom.cui
 * @date 2023/1/6
 * @description
 */
@RestController
@RequestMapping("/api/out-storage")
public class OutStorageController {

    @Autowired
    private OutStorageService outStorageService;

    @PostMapping("/list")
    public ResultModel<Page<OutStorageRecord>> queryInStorage(@RequestBody OutStorageRequest req) {
        return outStorageService.queryOutStorage(req);
    }

    @PostMapping("/add")
    public ResultModel<OutStorage> add(HttpSession session, @RequestBody OutStorage req) {
        Integer userId = (Integer)session.getAttribute(USER_ID);
        req.setUserId(userId);
        return outStorageService.add(req);
    }

    @GetMapping("/get/{id}")
    public ResultModel<OutStorageRecord> getRecord(@PathVariable("id") Integer id) {
        return outStorageService.getRecord(id);
    }
}
