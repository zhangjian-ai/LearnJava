package com.zhangjian.mapper;

import com.zhangjian.pojo.Emp;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {

    // 根据ID删除用户信息
    @Delete( "delete from emp where id = #{id};" )
    // 参数引用
    int delete(Integer id); // 返回一个 int 类型的受影响的行数。无果不需要，也可以直接使用 void

    // 批量删除员工
    int butchDelete(List<Integer> ids);

    // 添加用户数据
    @Options( useGeneratedKeys = true, keyProperty = "id" )
    @Insert( "insert into emp (username, name, gender, job, entry_date, dept_id, create_time, update_time)" +
            "values (#{username}, #{name}, #{gender}, #{job}, #{entryDate}, #{deptId}, #{createTime}, #{updateTime});" )
    void insert(Emp emp);

    // 修改数据
    @Update( "update emp set username = #{username}, name=#{name},gender=#{gender},image=#{image},job= #{job},dept_id=#{deptId},update_time=#{updateTime} where id=#{id};" )
    void update(Emp emp);

    // 动态修改数据
    void update2(Emp emp);

    // 根据ID查询
    @Select( "select * from emp where id = #{id}" )
    Emp selectById(Integer id);

    // 根据条件查询
//    @Select( "select * from emp where name like concat('%', #{name}, '%') and gender = #{gender} " +
//            "and entry_date between #{begin} and #{end} order by update_time desc ;" )
//    List<Emp> selectList(String name, byte gender, LocalDate begin, LocalDate end);

    // 根据条件查询-xml配置sql
    List<Emp> selectList(String name, byte gender, LocalDate begin, LocalDate end);
}