package com.zhangjian.music.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Order {
    ASC(true, "asc"),
    DESC(false, "desc"),
    NON(null, "non");

    private final Boolean asc;

    @JsonValue
    private final String desc;
}
