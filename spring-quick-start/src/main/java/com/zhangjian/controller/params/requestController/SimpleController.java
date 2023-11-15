package com.zhangjian.controller.params.requestController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class SimpleController {
    /**
     * 传统方式获取简单参数
     * 使用 HttpServletRequest 请求对象获取
     */
    @RequestMapping("simpleParams")
    public String demo1(HttpServletRequest request){
        // 通过 HttpServletRequest 拿到的参数，类型都是 String，业务使用时需要根据情况自行转换
        String name = request.getParameter("name");
        Integer age = Integer.parseInt(request.getParameter("age"));

        System.out.println(name + " : " + age);

        return "OK~";
    }

    /**
     * 使用Springboot方式获取请求参数，直接在形参中写出参数，由框架自动转换映射。需要 形参名 和 请求参数名 一致，才能正确映射
     *
     * 注意：
     *   1、如果 期望的形参名，在请求中并不存在，将获得对应类型的 默认值；
     *   2、这种获取请求参数的方式，不用区分 GET POST，即这些请求方式都可以使用这种方式获取请求参数
     */
    @RequestMapping("simpleParams1")
    public String demo2(String name, Integer age){
        System.out.println(name + " : " + age);

        return "OK~";
    }

    /**
     * 使用Springboot方式获取请求参数，如果 形参名 和 请求参数名 不一致，可以使用 RequestParam 注解将请求中的字段映射到方法参数上面
     *
     * 注意：
     *   1、 RequestParam 中使用的原字段，默认情况下是要求必填的
     */
    @RequestMapping("simpleParams2")
    public String demo3(@RequestParam(value = "username", required = true) String name, Integer age){ // 将请求中的 username 映射到 name，要求 username 必填。可使用 required 改为 非必填
        System.out.println(name + " : " + age);

        return "OK~";
    }

}
