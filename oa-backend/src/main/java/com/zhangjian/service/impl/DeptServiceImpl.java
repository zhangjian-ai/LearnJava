package com.zhangjian.service.impl;

import com.zhangjian.mapper.DeptMapper;
import com.zhangjian.po.DeptPO;
import com.zhangjian.service.DeptService;
import org.apache.ibatis.builder.ParameterExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<DeptPO> list() {
        return deptMapper.list();
    }

    @Override
    public DeptPO get(Integer id){
        return deptMapper.selectById(id);
    }

    @Override
    public void delete(Integer id) {
        deptMapper.deleteById(id);
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
