package com.zhangjian.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class ShowLog {

    @Pointcut("@annotation(com.zhangjian.aop.PrintLog)")
    public void pt(){}

    // 同时使用多个表达式
//    @Before("execution(* com.zhangjian.service.DeptService.delete(*)) || execution(* com.zhangjian..DeptService.list())")
    @Before("pt()")
    public void printLog(JoinPoint joinPoint){
        log.info(joinPoint.getSignature().getName() + " 被调用了~");

        // 获取目标对象 资源路径
        log.info(joinPoint.getSourceLocation().toString());

        // 获取类型
        log.info(joinPoint.getKind());

        // 获取 当前 连接点对象
        log.info(joinPoint.getThis().toString());
    }
}
