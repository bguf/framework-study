package com.bguf.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author gufb
 * @date 2021/8/24 11:36 AM
 */
@Slf4j
@Aspect
public class LogAspects {
    private JoinPoint joinPoint;

    // 抽取公共的切入点表达式
    @Pointcut("execution(public int com.bguf.aop.MathCalculator.*(..))")
    public void pointCut() {};

    // @Before在目标方法之前切入：切入点表达式
    // joinPoint要
    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint) {
        this.joinPoint = joinPoint;
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        log.info("{}方法运行--->参数列表是: {}", methodName, Arrays.toString(args));
    }

    @After("execution(public int com.bguf.aop.MathCalculator.div(int, int))")
    public void logEnd() {
        String methodName = joinPoint.getSignature().getName();
        log.info("{}方法运行结束", methodName);
    }

    @AfterReturning(value = "pointCut()", returning = "result")
    public void logReturn(Object result) {
        String methodName = joinPoint.getSignature().getName();
        log.info("{}方法正常返回，运行结果：{}", methodName, result);
    }

    @AfterThrowing(value = "pointCut()", throwing = "exception")
    public void logException(Exception exception) {
        String methodName = joinPoint.getSignature().getName();
        log.info("{}方法异常\n", methodName);
        log.error("", exception);
    }
}
