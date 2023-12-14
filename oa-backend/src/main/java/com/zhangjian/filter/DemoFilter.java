package com.zhangjian.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*") // 过滤的请求路径。此处表示所有请求
public class DemoFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("初始化 filter"); // 仅在程序启动时调用一次
    }

    /**
     * 只有这个方法必须实现。其他两个是 default 方法，不常用
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 放行前的操作，通常用于处理 request 相关内容，比如登录校验、访问权限验证等
        log.info("放行前的操作");

        // 放行。让请求访问后端资源
        filterChain.doFilter(servletRequest, servletResponse);

        // 放行后的操作。通常用于处理 response 相关内容，比如 响应改写等等
        log.info("放行后的操作");
    }

    @Override
    public void destroy() {
        log.info("销毁 filter"); // 仅在程序退出时调用一次
    }
}
