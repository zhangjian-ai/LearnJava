package com.zhangjian.music.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Gender {
    MALE(1, "男"),
    FEMALE(0, "女");

    @EnumValue
    private final int id;

    @JsonValue
    private final String desc;
}
