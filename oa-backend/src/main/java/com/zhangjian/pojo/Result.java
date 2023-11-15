package com.zhangjian.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * 响应实体类
 */
@Getter
@Setter
public class Result {
    private int code;
    private String msg;
    private Object data;

    private Result() {
    }

    private Result(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Result builder() {
        return new Result();
    }

    public static Result builder(int code, String msg, Object data) {
        return new Result(code, msg, data);
    }

    public static Result success() {
        return new Result(0, "success", null);
    }

    public static Result success(Object data) {
        return new Result(0, "success", data);
    }

    public static Result fail() {
        return new Result(-1, "fail", null);
    }

    public static Result fail(Object data) {
        return new Result(-1, "fail", data);
    }
}
