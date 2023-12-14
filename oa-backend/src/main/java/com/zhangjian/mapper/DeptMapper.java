package com.zhangjian.mapper;

import com.zhangjian.de.po.DeptPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeptMapper {

    /**
     * 部门列表
     * @return 部门列表
     */
    List<DeptPO> list();

    /**
     * 根据ID查询部门信息
     * @param id
     * @return
     */
    DeptPO selectById(Integer id);

    /**
     * 根据id删除部门
     * @param id 部门id
     */
    void deleteById(Integer id);

    /**
     * 新增部门。将新增部门自增的id回写到DeptPO
     * @param deptPO 部门实例
     */
    void insert(DeptPO deptPO);

    /**
     * 修改部门
     * @param deptPO
     */
    void update(DeptPO deptPO);

}
