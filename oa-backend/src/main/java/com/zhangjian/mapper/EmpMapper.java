package com.zhangjian.mapper;

import com.zhangjian.de.po.EmpPO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {

    /**
     * 查询
     */
    List<EmpPO> select(String name, Integer gender, LocalDate start, LocalDate end);

    /**
     * 删除
     */
    void delete(List<Integer> ids);

    /**
     * 插入
     */
    void insert(EmpPO emp);

    /**
     * 根据id查询
     * 由于本接口查询SQL很简单，因此直接使用@Select注解实现
     * @param id
     * @return
     */
    @Select("select * from emp where id = #{id}")
    EmpPO queryById(Integer id);

    /**
     * 更新
     * @param empPO
     */
    void update(EmpPO empPO);

    /**
     * 根据用户名和密码获取用户
     * @param empPO
     */
    @Select("select * from emp where username = #{username} and password = #{password}")
    EmpPO queryByUsernameAndPassword(EmpPO empPO);

    /**
     * 根据部门id删除员工
     * @param deptId
     */
    @Delete("delete from emp where dept_id = #{deptId}")
    void deleteByDeptId(Integer deptId);
}
