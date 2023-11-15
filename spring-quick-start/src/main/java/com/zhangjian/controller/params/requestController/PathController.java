package com.zhangjian.controller.params.requestController;

import org.springframework.web.bind.annotation.*;

/**
 * 路径参数：通过请求URL直接传递参数，使用 {参数名} 来标识路径参数，需要使用 @PathVariable 获取路径参数
 */
@RestController
public class PathController {

    @GetMapping("/pathParam/{id}/{name}")
    public String pathParam(Integer score, @PathVariable Integer id, @PathVariable String name){ // 路径参数和简单参数
        System.out.println(name + " " + id + " " + score);
        return "OK~";
    }
}
