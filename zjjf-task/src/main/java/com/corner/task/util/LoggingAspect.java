package com.corner.task.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by MXH on 2016/10/26.
 * 使用Aspect统计方法调用的时间
 */
@Aspect
@Component
public class LoggingAspect {
    //日志记录
    public Logger log = LoggerFactory.getLogger("reqTime_logger");

    /**
     * 统计Service中方法调用的时间
     * @param joinPoint
     * @throws Throwable
     */
    @Around("execution(* com.corner.task.service.*Service.*(..))")
    public Object logServiceMethodAccess(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object object = joinPoint.proceed();
        long end = System.currentTimeMillis();
        long t = end - start;
        if(t>=1000){
            String tmp = joinPoint.getSignature().toString();
            log.info("runTime：===================================="+String.format("class:%s,invoke_time:%s",tmp,t));
        }
        return object;
    }
}
