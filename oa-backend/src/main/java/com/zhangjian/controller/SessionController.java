package com.zhangjian.controller;

import com.zhangjian.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
public class SessionController {

    /**
     * 通过 HttpSession 获取session对象
     * @return
     */
    @GetMapping("/s1")
    public Result session01(HttpSession session){
        // 同一个浏览器访问服务时，session 同上是一样的
        log.info("s1 session: " + session.hashCode());

        // 在session中设置值
        session.setAttribute("queen", "jane");

        return Result.success();
    }

    /**
     * 通过 HttpServletRequest 也可以获取到session对象
     * @param request
     * @return
     */
    @GetMapping("/s2")
    public Result session02(HttpServletRequest request){
        // session 的 hashCode 和上面的接口应该是一样的
        HttpSession session = request.getSession();

        log.info("s2 session: " + session.hashCode());

        // 获取session的值
        Object queen = session.getAttribute("queen");
        log.info(queen.toString());

        return Result.success();
    }
}
