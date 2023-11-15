package com.zhangjian.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 员工持久化类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpPO {
    private int id;
    private String username;
    private String password;
    private String name;
    private byte gender;
    private String image;
    private byte job;
    private LocalDate entryDate;
    private int deptId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
