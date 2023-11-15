package com.zhangjian.controller.params.responseController;

import com.zhangjian.controller.params.pojo.Address;
import com.zhangjian.controller.params.pojo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明：
 * 在 springboot 框架中，如果需要将方法返回值作为请求的响应返回给客户端，就需要使用到 @ResponseBody 注解
 *
 * @ResponseBody 注解说明
 * 1、类型：方法注解、类注解
 * 2、位置：Controller方法上、类上
 * 3、作用：讲方法返回值作为响应结果，如果返回类型是 实体对象/集合，将会转换为 JSON格式 响应
 * 4、说明：@RestController = @Controller + @ResponseBody
 */
@RestController
public class ResponseController {

    @GetMapping("getString")
    public String getString() {
        // 返回一个字符串
        return "OK~";
    }

    @GetMapping("getAddress")
    public Address getAddress() {
        // 返回地址信息
        Address address = new Address();
        address.setProvince("四川");
        address.setCity("资阳");

        return address;
    }

    @GetMapping("getListAddress")
    public List<Address> getListAddress() {
        // 返回地址信息的列表
        ArrayList<Address> addresses = new ArrayList<>();

        Address address = new Address();
        address.setProvince("四川");
        address.setCity("资阳");
        addresses.add(address);

        Address address1 = new Address();
        address1.setProvince("重庆");
        address1.setCity("江津");
        addresses.add(address1);

        return addresses;
    }

    /**
     * 以上三个接口，返回值风格各异，前端获取到结果后，处理起来很不方便，尤其是系统中存在大量交互接口的时候
     * 因此，处于编程规范和开发的便捷，我们需要统一相应类型。让所有接口都遵循相同的相应类型
     */

    @GetMapping("getStringResult")
    public Result getStringResult() {
        return Result.success("OK~");
    }

    @GetMapping("getAddressResult")
    public Result getAddressResult() {
        // 返回地址信息
        Address address = new Address();
        address.setProvince("四川");
        address.setCity("资阳");

        return Result.success(address);
    }

    @GetMapping("getListAddressResult")
    public Result getListAddressResult() {
        // 返回地址信息的列表
        ArrayList<Address> addresses = new ArrayList<>();

        Address address = new Address();
        address.setProvince("四川");
        address.setCity("资阳");
        addresses.add(address);

        Address address1 = new Address();
        address1.setProvince("重庆");
        address1.setCity("江津");
        addresses.add(address1);

        return Result.success(addresses);
    }
}
