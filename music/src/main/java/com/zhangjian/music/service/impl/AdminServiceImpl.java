package com.zhangjian.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangjian.music.dao.AdminMapper;
import com.zhangjian.music.po.AdminPO;
import com.zhangjian.music.service.AdminService;
import org.springframework.stereotype.Service;


@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, AdminPO> implements AdminService {

    @Override
    public Boolean login(String name, String password) {
        QueryWrapper<AdminPO> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        wrapper.eq("password", password);

        AdminPO adminPO = baseMapper.selectOne(wrapper);

        return adminPO != null;
    }
}
