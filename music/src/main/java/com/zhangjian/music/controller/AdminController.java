package com.zhangjian.music.controller;

import com.zhangjian.music.dto.AdminDto;
import com.zhangjian.music.service.AdminService;
import com.zhangjian.music.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Api(tags = "后台管理")
public class AdminController {

    private final AdminService adminService;

    /**
     * 登录接口
     */
    @ApiOperation("管理员登录")
    @PostMapping("/login")
    public Result login(@RequestBody AdminDto adminDTO){

        Boolean login = adminService.login(adminDTO.getName(), adminDTO.getPassword());

        if (login) return Result.build(0, "登录成功", null);

        return Result.build(-1, "账号或密码错误", null);
    }
}
