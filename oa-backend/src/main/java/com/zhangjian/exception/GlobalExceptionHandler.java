package com.zhangjian.exception;

import com.zhangjian.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class) // 捕获的异常类型。这里捕获所有 Exception
    public Result ex(Exception exception){
        exception.printStackTrace();
        return Result.builder(-1, exception.getMessage(), "系统异常");
    }
}
