package com.zhangjian.music.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {
    MALE(1, "男"),
    FEMALE(0, "女");

    @EnumValue
    private final int id;

    @JsonValue
    private final String desc;

    Gender(int id, String desc) {
        this.id = id;
        this.desc = desc;
    }
}
