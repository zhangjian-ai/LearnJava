package com.zhangjian.aop;

import com.alibaba.fastjson.JSON;
import com.zhangjian.mapper.OperateLogMapper;
import com.zhangjian.de.po.OperateLogPO;
import com.zhangjian.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class OperateLogAspect {

    @Autowired
    private HttpServletRequest httpServletRequest; // 在spring中，请求对象也是被IOC容器管理的

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around( "@annotation(com.zhangjian.aop.OperateLog)" )
    public Object recordOperateLog(ProceedingJoinPoint joinPoint) throws Throwable {
        // 1. 通过请求头获取jwt，解析得到操作人信息
        String jwt = httpServletRequest.getHeader("token");
        Claims claims = JwtUtils.parseJwt(jwt);
        Object id = claims.get("id");
        Object name = claims.get("name");

        String operateUser = name + "(id=" + id + ")";

        // 2. 操作时间
        LocalDateTime operateTime = LocalDateTime.now();

        // 3. 执行方法的全类名
        String className = joinPoint.getTarget().getClass().getName();

        // 4. 执行的方法名
        String methodName = joinPoint.getSignature().getName();

        // 5. 调用方法时，使用到的参数
        Object[] args = joinPoint.getArgs();
        String methodParams = Arrays.toString(args).substring(0, 150);

        // 6. 执行方法回去返回值
        long begin = System.currentTimeMillis();

        Object result = joinPoint.proceed();
        String returnValue = JSON.toJSONString(result);

        long end = System.currentTimeMillis();

        // 7. 获取执行时长
        Integer costTime = (int) (end - begin);

        // 记录日志
        OperateLogPO operateLogPO = new OperateLogPO(null, operateUser, operateTime, className, methodName, methodParams, returnValue, costTime);
        operateLogMapper.insert(operateLogPO);

        log.info("新增一条操作日志: {}", operateLogPO);

        // 把原始方法的返回值返回回去
        return result;
    }
}
