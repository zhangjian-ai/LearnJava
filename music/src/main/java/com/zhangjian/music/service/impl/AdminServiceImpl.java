package com.zhangjian.music.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangjian.music.dao.AdminMapper;
import com.zhangjian.music.po.AdminPO;
import com.zhangjian.music.service.AdminService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, AdminPO> implements AdminService {
    @Override
    public Boolean login(String name, String password) {
        List<AdminPO> list = lambdaQuery()
                .eq(AdminPO::getName, name)
                .eq(AdminPO::getPassword, password)
                .list();

        return list.size() == 1;
    }
}
