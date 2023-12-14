package com.zhangjian.controller;

import cn.hutool.core.bean.BeanUtil;
import com.zhangjian.de.dto.UserDto;
import com.zhangjian.de.dto.UserQueryDto;
import com.zhangjian.de.po.UserPo;
import com.zhangjian.de.vo.PageResultVo;
import com.zhangjian.de.vo.UserVo;
import com.zhangjian.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping( "/users" )
@RestController
@RequiredArgsConstructor
@Api( tags = "用户管理" )
public class UserController {

    /**
     * spring在controller中直接使用 Autowired 直接注入属性，推荐使用构造器。
     * 这里使用 final + RequiredArgsConstructor 实现bean的注入
     */
    private final UserService userService;


    @ApiOperation( "新增用户" )
    @PostMapping
    public void addUser(@RequestBody UserDto userDto) {
        // 1. 将 dto 转为 po
        UserPo userPo = BeanUtil.copyProperties(userDto, UserPo.class);

        // 2. 添加 创建时间 和 更新时间
        userPo.setCreateTime(LocalDateTime.now());
        userPo.setUpdateTime(LocalDateTime.now());

        // 3. 添加数据
        userService.save(userPo);
    }


    @ApiOperation( "删除用户" )
    @DeleteMapping( "{id}" )
    public void deleteUserById(@ApiParam( "用户id" ) @PathVariable( "id" ) Integer id) {
        // 1. 删除用户
        userService.removeById(id);
    }


    @ApiOperation( "根据ID查询用户" )
    @GetMapping( "{id}" )
    public UserVo queryUserById(@ApiParam( "用户id" ) @PathVariable( "id" ) Integer id) {
//        // 1. 查询用户
//        UserPo userPo = userService.getById(id);
//
//        // 2. 将 po 转成 vo 返回
//        return BeanUtil.copyProperties(userPo, UserVo.class);

        // 同时查询 用户及其地址，是一个复杂查询，将逻辑放到service中
        return userService.queryUserAndAddress(id);
    }


    @ApiOperation( "根据ID集合批量查询用户" )
    @GetMapping
    public List<UserVo> queryUserByIds(@ApiParam( "用户id集合" ) @RequestParam( "ids" ) List<Integer> ids) {
//        // 1. 查询用户
//        List<UserPo> userPos = userService.listByIds(ids);
//
//        // 2. 将 po集合 转成 vo集合 返回
//        return BeanUtil.copyToList(userPos, UserVo.class);
        return userService.queryUserAndAddressByIds(ids);
    }


    @ApiOperation( "根据ID扣减用户薪资" )
    @PutMapping( "{id}/deduction/{money}" )
    public void deductMoneyById(
            @ApiParam( "用户id" ) @PathVariable( "id" ) Integer id,
            @ApiParam( "扣减金额" ) @PathVariable( "money" ) Integer money) {

        // 扣减用户金额的操作，是一个复杂逻辑，IService 没有提供现成的方法，需要自己实现
        userService.deductMoneyById(id, money);
    }


    @ApiOperation( "根据条件批量查询用户" )
    @GetMapping("list")
    public List<UserVo> queryUsers(UserQueryDto userQueryDto) { // get请求使用实体接收参数时，不需要任何注解
        // 1. 查询用户
        List<UserPo> userPos = userService.listByConditions(userQueryDto.getUsername(), userQueryDto.getGender(), userQueryDto.getStart(), userQueryDto.getEnd());

        // 2. 将 po集合 转成 vo集合 返回
        return BeanUtil.copyToList(userPos, UserVo.class);
    }


    @ApiOperation( "根据条件分页查询用户" )
    @GetMapping("page")
    public PageResultVo<UserVo> queryUsersByPage(UserDto userDto) {
        return userService.queryUsersByPage(userDto);
    }

}
