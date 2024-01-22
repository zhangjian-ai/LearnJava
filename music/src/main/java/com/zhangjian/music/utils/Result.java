package com.zhangjian.music.utils;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Result {
    /**
     * 状态码 0 正常；-1 异常
     */
    private int code;
    private String msg;
    private Object data;

    public static Result build() {
        return new Result();
    }

    public static Result build(int code, String msg, Object data) {
        return new Result(code, msg, data);
    }

    public static Result success() {
        return new Result(0, "success", null);
    }

    public static Result success(Object data) {
        return new Result(0, "success", data);
    }

    public static Result error() {
        return new Result(-1, "error", null);
    }

    public static Result error(Object data) {
        return new Result(-1, "error", data);
    }
}
