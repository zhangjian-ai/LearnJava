package com.zhangjian.service;

import com.zhangjian.de.po.EmpPO;
import com.zhangjian.de.vo.EmpPageVO;

import java.time.LocalDate;
import java.util.List;


public interface EmpService {

    /**
     * 查询
     */
    EmpPageVO query(String name, Integer gender, LocalDate start, LocalDate end, Integer page, Integer pageSize);

    /**
     * 删除
     */
    void delete(List<Integer> ids);

    /**
     * 新增
     */
    void add(EmpPO emp);

    /**
     * 根据id查询员工信息
     * @param id
     * @return
     */
    EmpPO queryById(Integer id);

    /**
     * 修改员工信息
     * @param empPO
     */
    void update(EmpPO empPO);

    /**
     * 员工登录
     * @param empPO
     */
    EmpPO login(EmpPO empPO);
}
