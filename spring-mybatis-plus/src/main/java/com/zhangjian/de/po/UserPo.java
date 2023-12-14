package com.zhangjian.de.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "emp", autoResultMap = true) // 自动处理嵌套实体的映射
public class UserPo {
    @TableId(type = IdType.AUTO)
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
    private Integer salary;

    @TableField(typeHandler = JacksonTypeHandler.class )  // 实现 实体类属性 和 数据库字段的转换
    private AccountInfoPo accountInfo;
}
