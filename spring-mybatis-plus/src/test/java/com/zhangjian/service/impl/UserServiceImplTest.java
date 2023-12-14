package com.zhangjian.service.impl;

import com.zhangjian.de.po.UserPo;
import com.zhangjian.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void testSave(){
        UserPo userPo = new UserPo();
        userPo.setUsername("faker");
        userPo.setName("诡术妖姬");
        userPo.setGender((byte)1);
        userPo.setCreateTime(LocalDateTime.now());
        userPo.setUpdateTime(LocalDateTime.now());

        userService.save(userPo);

        System.out.println("新增用户成功，用户ID= " + userPo.getId());
    }

    @Test
    public void testQuery(){
        List<UserPo> userPoList = userService.listByIds(List.of(5, 6, 7, 8));
        userPoList.forEach(System.out::println);
    }

    public UserPo build(int idx){
        UserPo userPo = new UserPo();
        userPo.setUsername("faker" + idx);
        userPo.setName("诡术妖姬" + idx);
        userPo.setGender((byte)1);
        userPo.setCreateTime(LocalDateTime.now());
        userPo.setUpdateTime(LocalDateTime.now());

        return userPo;
    }

    @Test
    public void testSaveBatch(){
        // 开始时间
        long start = System.currentTimeMillis();

        // 插入1000条数据，使用批量插入，每次插入 100 个
        ArrayList<UserPo> userPoArrayList = new ArrayList<>(100);
        for (int i = 1; i <= 1000; i++) {

                userPoArrayList.add(build(i));

                if (i % 100 == 0){
                    userService.saveBatch(userPoArrayList);
                    userPoArrayList.clear();
                }
        }

        System.out.println("新增完成，耗时: " + (System.currentTimeMillis() - start));
    }

}