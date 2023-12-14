package com.zhangjian.de.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 部门持久化类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeptPO {
    private int id;
    private String name;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
