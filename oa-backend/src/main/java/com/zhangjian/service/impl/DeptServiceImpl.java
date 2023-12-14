package com.zhangjian.service.impl;

import com.zhangjian.aop.PrintLog;
import com.zhangjian.mapper.DeptMapper;
import com.zhangjian.mapper.EmpMapper;
import com.zhangjian.de.po.DeptPO;
import com.zhangjian.de.po.RecordPO;
import com.zhangjian.service.DeptService;
import com.zhangjian.service.RecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private RecordService recordService;

    @PrintLog
    @Override
    public List<DeptPO> list() {
        return deptMapper.list();
    }

    @Override
    public DeptPO get(Integer id){
        return deptMapper.selectById(id);
    }

    @PrintLog
    // 当前事务回滚时，不会影响日志记录的事务。因为它的 propagation = Propagation.REQUIRES_NEW
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Integer id) throws Exception {
        try {
            // 删除部门
            deptMapper.deleteById(id);

//        int a = 1/0;
//            if(true){
//                throw new Exception("运行出错啦~");
//            }

            // 删除员工
            empMapper.deleteByDeptId(id);
        } finally { // 将日志记录放在 finally 中，无论如何都会执行记录操作。且记录日志的事务不受当前事务影响
            RecordPO recordPO = new RecordPO();
            recordPO.setCreateTime(LocalDateTime.now());
            recordPO.setOperate("执行了删除部门的操作，被删除的部门ID为 " + id);
            recordService.add(recordPO);
        }
    }

    @Override
    public DeptPO add(DeptPO deptPO) {
        // 补充信息
        deptPO.setCreateTime(LocalDateTime.now());
        deptPO.setUpdateTime(LocalDateTime.now());

        deptMapper.insert(deptPO);

        return deptPO;
    }

    @Override
    public DeptPO update(DeptPO deptPO) {
        if(deptPO.getId() == 0){
            throw new RuntimeException("部门ID必填");
        }

        if(deptPO.getName() == null){
            throw new RuntimeException("部门名称必填");
        }

        deptPO.setUpdateTime(LocalDateTime.now());

        deptMapper.update(deptPO);

        return get(deptPO.getId());
    }
}
