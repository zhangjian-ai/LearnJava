package com.zhangjian.controller.params.requestController;

import com.zhangjian.controller.params.pojo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * POJO全称Plain Ordinary Java Object，表示简单的Java对象
 * 当参数个数较多时，通常将多个参数封装到一个对象
 */
@RestController
public class PojoController {

    /**
     * 使用实体参数时，要求请求参数名，和实例属性名一样
     *
     * 注意：如果属性本身也是一个对象
     *   1、那么在请求中以 属性名.引用对象的属性名 作为请求参数名。
     *      例如：http://localhost:8080/simplePojo?name=juju2&age=30&address.province=四川&address.city=遂宁
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "simplePojo", method = {RequestMethod.GET})
    public String simplePojo(User user){
        System.out.println(user);
        return "OK~";
    }
}
