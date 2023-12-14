package com.zhangjian.controller;

import com.zhangjian.de.po.EmpPO;
import com.zhangjian.pojo.Result;
import com.zhangjian.service.EmpService;
import com.zhangjian.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody EmpPO empPO){
        log.info("用户 {} 登录", empPO.getUsername());

        EmpPO emp = empService.login(empPO);

        // 登录成功则生成JWT返回
        if (emp != null){
            // 准备payload
            HashMap<String, Object> payload = new HashMap<>();
            payload.put("id", emp.getId());
            payload.put("name", emp.getName());
            payload.put("username", emp.getUsername());

            String jwt = JwtUtils.generateJwt(payload);

            return Result.success(jwt);
        }

        // 如果查到用户表示登录成功；否则表示登录失败
        return Result.fail("用户名或密码错误");
    }
}
