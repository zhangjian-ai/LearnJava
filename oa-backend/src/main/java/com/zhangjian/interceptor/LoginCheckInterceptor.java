package com.zhangjian.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.zhangjian.pojo.Result;
import com.zhangjian.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    /**
     * 登录校验重写这一个方法即可
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. 获取请求url
        String uri = request.getRequestURI();
        log.info("请求后端资源: {}", uri);

        // 2.判断是否是登录接口。拦截器配置时直接配置排除 /login 不拦截即可

        // 3. 获取请求头中的jwt令牌（token字段）
        String jwt = request.getHeader("token");

        // 4. 判断令牌是否存在不存在则返回错误响应
        if (!StringUtils.hasLength(jwt)){
            log.error("token 信息为空");
            Result err = Result.builder(-1, "INVALID_TOKEN", null);

            // 在controller中，因为 Controller 注解的存在，自动帮我们将Result对象，转成了 JSON串
            // 然而在 Interceptor 中，就需要我们自己转换并写入到响应中
            String noLogin = JSONObject.toJSONString(err);
            response.getWriter().write(noLogin);
            return false;
        }

        // 5. 判断令牌是否合法，不合法也返回错误响应
        try {
            JwtUtils.parseJwt(jwt);
        } catch (Exception e) {
            e.printStackTrace();

            log.error("token 校验失败");
            Result err = Result.builder(-1, "INVALID_TOKEN", null);
            String noLogin = JSONObject.toJSONString(err);
            response.getWriter().write(noLogin);
            return false;
        }

        // 6. 如果校验通过，则放行
        return true;
    }
}
