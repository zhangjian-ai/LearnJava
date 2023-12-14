package com.zhangjian;

import com.zhangjian.mapper.EmpMapper;
import com.zhangjian.pojo.BuilderDemo;
import com.zhangjian.pojo.Emp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class SpringMybatisApplicationTests {

    @Autowired
    private EmpMapper empMapper;

    @Test
    public void testDeleteEmp(){
        int delete = empMapper.delete(12);
        System.out.println(delete); // 1  删除一条数据
    }

    @Test
    public void testInsertEmp(){
        Emp emp = new Emp();
        emp.setUsername("Tom2");
        emp.setName("汤姆2");
        emp.setGender((byte)1);
        emp.setJob((byte)2);
        emp.setEntryDate(LocalDate.of(2023, 10, 14)); // 创建 年月日的 日期对象
        emp.setDeptId(2);
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());

        empMapper.insert(emp);

        System.out.println("新增数据成功，主键 id=" + emp.getId());
    }

    @Test
    public void testUpdateEmp(){
        Emp emp = new Emp();
        emp.setId(13);
        emp.setUsername("Tom1");
        emp.setName("汤姆1");
        emp.setImage("001.png");
        emp.setGender((byte)1);
        emp.setJob((byte)2);
        emp.setDeptId(2);
        emp.setUpdateTime(LocalDateTime.now());

        empMapper.update(emp);
    }

    @Test
    public void testUpdate2Emp(){ // 根据传入条件动态的修改员工信息
        Emp emp = new Emp();
        emp.setId(13);
        emp.setUsername("Tom101");
        emp.setName("汤姆101");
        emp.setUpdateTime(LocalDateTime.now());

        empMapper.update(emp);
    }

    @Test
    public void testSelectById(){
        Emp emp = empMapper.selectById(14);
        System.out.println(emp);
    }

    @Test
    public void testSelectList(){
//        List<Emp> empList = empMapper.selectList("者", (byte) 1, LocalDate.of(2010, 1, 1), LocalDate.of(2025, 1, 1));
//        List<Emp> empList = empMapper.selectList("者", (byte) 1, null, null);
        List<Emp> empList = empMapper.selectList(null, (byte) 1, null, null);
        System.out.println(empList);
    }

    @Test
    public void testBatchDelete(){
        List<Integer> list = Arrays.asList(13, 14);
        int butchDelete = empMapper.butchDelete(list);

        System.out.println("删除 " + butchDelete + " 条数据");
    }

    @Test
    public void testBuilderDemo(){
        BuilderDemo builderDemo = BuilderDemo.builder()
                .name("王爷")
                .age(32)
                .build();
        System.out.println(builderDemo);
    }
}
