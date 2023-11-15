package com.zhangjian.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Emp {
    /**
     * 员工实体类
     */
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
