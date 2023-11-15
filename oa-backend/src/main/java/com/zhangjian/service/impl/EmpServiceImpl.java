package com.zhangjian.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhangjian.mapper.EmpMapper;
import com.zhangjian.po.EmpPO;
import com.zhangjian.service.EmpService;
import com.zhangjian.vo.EmpPageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Override
    public EmpPageVO query(String name, Integer gender, LocalDate start, LocalDate end, Integer page, Integer pageSize) {
        // 1. 设置分页
        PageHelper.startPage(page, pageSize);

        // 2. 执行查询
        List<EmpPO> empPOS = empMapper.select(name, gender, start, end);

        // 3. 将结果装入EmpPageVO
        Page<EmpPO> empPOPage = (Page<EmpPO>)empPOS;

        EmpPageVO empPageVO = new EmpPageVO();

        empPageVO.setTotal(empPOPage.getTotal());
        empPageVO.setRows(empPOPage.getResult());

        return empPageVO;
    }

    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    @Override
    public void add(EmpPO emp) {
        // 补充创建时间和更新时间信息
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());

        empMapper.insert(emp);
    }

    @Override
    public EmpPO queryById(Integer id) {
        return empMapper.queryById(id);
    }

    @Override
    public void update(EmpPO empPO) {
        // 刷新更新时间
        empPO.setUpdateTime(LocalDateTime.now());
        empMapper.update(empPO);
    }

    @Override
    public EmpPO login(EmpPO empPO) {
        return empMapper.queryByUsernameAndPassword(empPO);
    }
}
