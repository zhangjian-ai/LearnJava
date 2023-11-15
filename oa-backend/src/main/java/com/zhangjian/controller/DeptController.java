package com.zhangjian.controller;

import com.zhangjian.po.DeptPO;
import com.zhangjian.pojo.Result;
import com.zhangjian.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * 批量查询
     */
    @GetMapping
    public Result list(){
        log.info("查询所有部门信息");
        return Result.success(deptService.list());
    }

    /**
     * 根据部门ID删除部门
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("删除部门 id={}", id);
        deptService.delete(id);
        return Result.success();
    }

    /**
     * 新增部门
     */
    @PostMapping
    public Result add(@RequestBody DeptPO deptPO){
        log.info("新增部门: {}", deptPO.getName());
        return Result.success(deptService.add(deptPO));
    }

    /**
     * 修改部门
     */
    @PutMapping
    public Result update(@RequestBody DeptPO deptPO){
        log.info("修改部门信息，要修改的部门 id={}", deptPO.getId());
        return Result.success(deptService.update(deptPO));
    }
}
