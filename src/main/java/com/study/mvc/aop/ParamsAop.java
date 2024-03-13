package com.study.mvc.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ParamsAop {
    @Pointcut("@annotation(com.study.mvc.aop.annotation.ParamsAspect)")
    private void pointCut(){}

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        Object[] args = proceedingJoinPoint.getArgs();
//        for(Object obj : args) {
//            log.info("{}", obj);
//        }


        CodeSignature codeSignature = (CodeSignature) proceedingJoinPoint.getSignature();
        String[] paramName = codeSignature.getParameterNames();
        String className = codeSignature.getDeclaringTypeName();
        String methodName = codeSignature.getName();

        for(int i = 0; i < args.length; i++) {
            log.info("{}.{} >>> {}: {}",className, methodName, paramName[i], args[i]);
        }
        for(int i = 0; i < args.length; i++) {
            log.info("{}: {} ({}.{})  ",paramName[i], args[i],className, methodName);
        }



        log.info("전처리");

        Object result = proceedingJoinPoint.proceed(); // 비즈니스로직

//        log.info("후처리");
//        log.info("response: {}", result);

        return result;
    }
}
