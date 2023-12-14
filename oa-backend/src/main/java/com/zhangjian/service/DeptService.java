package com.zhangjian.service;

import com.zhangjian.de.po.DeptPO;

import java.util.List;

public interface DeptService {

    /**
     * 部门列表
     * @return
     */
    List<DeptPO> list();

    /**
     * 查询部门
     */
    DeptPO get(Integer id);

    /**
     * 删除部门
     */
    void delete(Integer id) throws Exception;

    /**
     * 新增部门
     * @param deptPO
     */
    DeptPO add(DeptPO deptPO);

    /**
     * 修改部门
     * @param deptPO
     * @return
     */
    DeptPO update(DeptPO deptPO);
}
