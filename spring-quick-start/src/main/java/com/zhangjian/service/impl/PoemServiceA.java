package com.zhangjian.service.impl;

import com.zhangjian.dao.PoemDao;
import com.zhangjian.dao.impl.PoemDaoA;
import com.zhangjian.service.PoemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PoemServiceA implements PoemService {

    @Resource(name = "poemDaoA")
    private PoemDao poemDao;

    @Override
    public String addRight() {
        String content = poemDao.read();

        return content + "\n\t\t\t\t 唐.李白";
    }
}
