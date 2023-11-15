package com.zhangjian.controller.params.requestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * 数组：数组参数名与形参中数组变量名相同，可以直接使用数组封装
 *      http://localhost:8080/arrayParam?hobby=python&hobby=java&hobby=ruby
 *
 * 集合：数组参数名与形参中数组变量名相同，通过 @RequestParam 绑定参数关系
 *
 */
@RestController
public class ArrayCollectionController {

    @GetMapping("arrayParam")
    public String arrayParam(String[] hobby){
        System.out.println(Arrays.toString(hobby));
        return "OK~";
    }

    @GetMapping("collectionParam")
    public String collectionParam(@RequestParam List<String> hobby){
        System.out.println(hobby.getClass()); // class java.util.ArrayList
        System.out.println(hobby);
        return "OK~";
    }
}
