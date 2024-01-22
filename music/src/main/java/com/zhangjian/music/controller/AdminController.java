package com.zhangjian.music.controller;

import com.zhangjian.music.po.AdminPO;
import com.zhangjian.music.service.AdminService;
import com.zhangjian.music.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * 登录接口
     */
    @PostMapping("/login")
    public Result login(AdminPO adminPO){
        Boolean login = adminService.login(adminPO.getName(), adminPO.getPassword());

        if (login) return Result.build(0, "登录成功", null);

        return Result.build(-1, "账号或密码错误", null);
    }
}
