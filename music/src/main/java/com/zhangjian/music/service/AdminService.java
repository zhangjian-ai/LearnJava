package com.zhangjian.music.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangjian.music.po.AdminPO;


public interface AdminService extends IService<AdminPO> {

    /**
     * 登录接口
     * @param name
     * @param password
     * @return
     */
    public Boolean login(String name, String password);
}
