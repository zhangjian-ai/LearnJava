package com.zhangjian.controller;

import com.zhangjian.po.EmpPO;
import com.zhangjian.pojo.Result;
import com.zhangjian.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody EmpPO empPO){
        log.info("用户 {} 登录", empPO.getUsername());

        EmpPO emp = empService.login(empPO);

        // 如果查到用户表示登录成功；否则表示登录失败
        return emp == null ? Result.fail("用户名或密码错误") : Result.success("登录成功");
    }
}
