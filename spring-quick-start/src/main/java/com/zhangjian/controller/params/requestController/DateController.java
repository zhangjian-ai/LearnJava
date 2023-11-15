package com.zhangjian.controller.params.requestController;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * 日期参数：使用 @DateTimeFormat 注解完成日期参数格式转换。要求请求参数的时间格式和注解中时间日期的格式保持一致
 */
@RestController
public class DateController {

    @GetMapping("dateParam")
    public String dateParam(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime updateTime) {
        System.out.println(updateTime);
        return "OK~";
    }
}
