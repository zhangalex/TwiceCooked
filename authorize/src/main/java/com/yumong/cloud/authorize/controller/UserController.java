package com.yumong.cloud.authorize.controller;

import com.yumong.cloud.authorize.service.UserService;
import com.yumong.cloud.support.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService us;

    @GetMapping("")
    public ResponseEntity index(@RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
                                @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                                @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword) {

        return ResponseEntity.ok(ResultData.SUCCESS(null));
    }
}
