package com.zhangjian.service.impl;

import com.zhangjian.mapper.RecordMapper;
import com.zhangjian.de.po.RecordPO;
import com.zhangjian.service.RecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordMapper recordMapper;

    // 新增日志记录方法被其他事物方法调用时，自己单独开一个事物，不受调用方事务影响
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void add(RecordPO recordPO) {
        Integer recordId = recordMapper.insert(recordPO);
        log.info("新增日志记录成功，id = {}", recordId);
    }
}
