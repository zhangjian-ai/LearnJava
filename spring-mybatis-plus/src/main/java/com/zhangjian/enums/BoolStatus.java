package com.zhangjian.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum BoolStatus {
    TRUE(1, "是"),
    FALSE(0, "否");

    @EnumValue // mp提供的注解，标记字段与数据库字段映射。实现实体类中枚举变量和数据库字段的转换
    private final int value;

    @JsonValue // spring 提供的注解。spring 在处理响应json时，遇到枚举默认是将枚举实例名字返回，使用这个注解后，就可以返回枚举中具体的字段
    private final String desc;

    BoolStatus(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
