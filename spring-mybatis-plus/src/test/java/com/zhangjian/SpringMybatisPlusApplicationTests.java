package com.zhangjian;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zhangjian.mapper.UserMapper;
import com.zhangjian.de.po.UserPo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class SpringMybatisPlusApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testGetUserById(){
        UserPo userPo = userMapper.selectById(16);
        System.out.println(userPo);
    }

    @Test
    public void testAddUser(){
        UserPo userPo = new UserPo();
        userPo.setUsername("fiona");
        userPo.setName("无双剑姬");
        userPo.setGender((byte)1);
        userPo.setCreateTime(LocalDateTime.now());
        userPo.setUpdateTime(LocalDateTime.now());

        userMapper.insert(userPo);

        System.out.println("新增用户成功，用户ID= " + userPo.getId());
    }

    @Test
    public void testQueryWrapper(){
        // 1. 构造查询条件
        QueryWrapper<UserPo> wrapper = new QueryWrapper<UserPo>()
                .select("id", "username", "name", "gender") // 查询的字段
                .like("name", "人") // 模糊匹配
                .ge("entry_date", LocalDate.of(2018, 1, 1)); // 范围查询

        // 2. 执行查询
        List<UserPo> userPoList = userMapper.selectList(wrapper);

        userPoList.forEach(System.out::println);
    }

    @Test
    public void testUpdateByQueryWrapper(){
        // 1. 要更新的内容用实体表示
        UserPo userPo = new UserPo();
        userPo.setPassword("12345678");

        // 2. 更新的条件
        QueryWrapper<UserPo> wrapper = new QueryWrapper<UserPo>()
                .eq("username", "xander");

        // 3. 执行更新
        int rows = userMapper.update(userPo, wrapper);

        System.out.println("更新影响行数: " + rows);
    }

    @Test
    public void testUpdateWrapper(){
        // 1. 直接构造更新语句。这里要操作每一行数据。直接 setSql
        UpdateWrapper<UserPo> updateWrapper = new UpdateWrapper<UserPo>()
                .setSql("password = concat('emp_', password)");

        // 2. 执行更新。此时是更新所有数据，没有使用实体来表示更新内容，name直接给一个 null 就可以
        int rows = userMapper.update(null, updateWrapper);

        System.out.println("更新影响行数: " + rows);
    }

    @Test
    public void testLambdaQueryWrapper(){
        // 1. 构造查询条件
        LambdaQueryWrapper<UserPo> wrapper = new LambdaQueryWrapper<UserPo>()
                .select(UserPo::getId, UserPo::getUsername, UserPo::getName, UserPo::getGender) // 查询的字段
                .like(UserPo::getName, "人") // 模糊匹配
                .ge(UserPo::getEntryDate, LocalDate.of(2018, 1, 1)); // 范围查询

        // 2. 执行查询
        List<UserPo> userPoList = userMapper.selectList(wrapper);

        userPoList.forEach(System.out::println);
    }
}
