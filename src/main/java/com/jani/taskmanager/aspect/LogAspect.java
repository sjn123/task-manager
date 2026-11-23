package com.jani.taskmanager.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author Jani
 * @data 2026/11/23 20:08
 **/
@Aspect // 告诉 Spring：我是一个切面（探头）
@Component // 交给 Spring 管理
public class LogAspect {

    // 1. 定义切点（Pointcut）：我们要拦哪里？
    // 这里的表达式意思是：拦截 com.jani.taskmanager.controller 包下的所有类的所有方法
    @Pointcut("execution(* com.jani.taskmanager.controller.*.*(..))")
    public void controllerPointcut() {}

    // 2. 定义通知（Advice）：拦截到之后要干嘛？
    // @Around 代表环绕通知，也就是在目标方法执行前和执行后，都可以插入逻辑
    @Around("controllerPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取被拦截的方法名
        String methodName = joinPoint.getSignature().getName();

        // 记录开始时间
        long startTime = System.currentTimeMillis();

        // 执行目标方法（也就是你真正的 Controller 里的业务代码）
        Object result = joinPoint.proceed();

        // 记录结束时间
        long endTime = System.currentTimeMillis();

        // 打印日志
        System.out.println("🔍 [AOP监控] 执行了方法: " + methodName + "，耗时: " + (endTime - startTime) + " ms");

        // 把结果还给前端
        return result;
    }
}