package com.zhangjian.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Component // 切面类都要交给IOC容器管理
@Aspect // 申明当前类是一个aop类
public class TimeRecord {

    // 切入点表达式。此处表示 controller 中所有类的所有方法都将走这个切面
//    @Around("execution(* com.zhangjian.controller.*.*(..))")
    @Around("com.zhangjian.aop.ShowLog.pt()")
    public Object record(ProceedingJoinPoint joinPoint) throws Throwable {
        // 记录开始时间
        long begin = System.currentTimeMillis();

        // 获取目标类名
        log.info("连接点类名: {}", joinPoint.getTarget().getClass().getName());

        // 获取目标方法
        log.info("当前方法名: {}", joinPoint.getSignature().getName());

        // 获取方法运行时的参数
        log.info("参数信息: {}", joinPoint.getArgs());

        // 执行原始方法。通过代理调用时，返回类型统一为 Object
        Object result = joinPoint.proceed();

        // 记录结束时间
        long end = System.currentTimeMillis();

        log.info(joinPoint.getSignature() + " 方法耗时: {} ms", end - begin);

        return result;
    }
}
