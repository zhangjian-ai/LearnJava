package com.zhangjian.controller.params.requestController;

import com.zhangjian.controller.params.pojo.User;
import org.springframework.web.bind.annotation.*;

/**
 * JSON参数：JSON数据键名与形参对象属性名相同，定义POJO类型形参即可接收参数，需要使用 @RequestBody 标识
 */
@RestController
public class JsonController {

    @PostMapping("jsonParam")
    public String jsonParam(@RequestBody User user){
        System.out.println(user);
        return "OK~";
    }
}
