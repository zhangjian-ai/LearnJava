package com.zhangjian.controller.params.pojo;

/**
 * 响应结果类
 *
 *  code：0 表示成功；-1 表示失败
 *  msg：当前响应的描述信息
 *  data：实际响应的对象，可以是任何类型
 */
public class Result {
    private int code;
    private String msg;
    private Object data;

    public Result(){};

    public Result(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Result success(){
        return new Result(0, "success", null);
    }

    public static Result success(Object data){
        return new Result(0, "success", data);
    }

    public static Result fail(){
        return new Result(-1, "fail", null);
    }

    public static Result fail(Object data){
        return new Result(-1, "fail", data);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
