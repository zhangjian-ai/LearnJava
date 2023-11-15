package com.zhangjian.controller;

import com.zhangjian.po.EmpPO;
import com.zhangjian.pojo.Result;
import com.zhangjian.service.EmpService;
import com.zhangjian.vo.EmpPageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;

    /**
     * 员工分页查询
     * @param name
     * @param gender
     * @param start
     * @param end
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping
    public Result query(String name, Integer gender,
                        @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
                        @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
                        @RequestParam(defaultValue = "1") Integer page,
                        @RequestParam(defaultValue = "10") Integer pageSize){
        log.info("查询员工信息");
        EmpPageVO empPageVO = empService.query(name, gender, start, end, page, pageSize);

        return Result.success(empPageVO);
    }

    /**
     * 根据id批量删除员工
     * @param ids
     * @return
     */
    @DeleteMapping("/{ids}") // 路径参数 多个id用 , 隔开。接收时可以转为 数组 或 集合
    public Result delete(@PathVariable List<Integer> ids){
        log.info("删除员工信息: " + ids);
        empService.delete(ids);
        return Result.success();
    }

    /**
     * 新增员工
     * @param emp
     * @return
     */
    @PostMapping
    public Result add(@RequestBody EmpPO emp){
        log.info("新增员工: {}", emp);
        empService.add(emp);

        return Result.success(emp);
    }

    /**
     * 根据id查询员工信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result queryById(@PathVariable Integer id){
        log.info("查询ID为 {} 的员工信息", id);
        EmpPO empPO = empService.queryById(id);
        return Result.success(empPO);
    }

    /**
     * 修改员工信息
     * @param empPO
     * @return
     */
    @PutMapping
    public Result update(@RequestBody EmpPO empPO){
        log.info("更新员工信息");
        empService.update(empPO);
        return Result.success();
    }
}
