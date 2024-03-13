package com.study.mvc.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Slf4j
@Aspect
@Component
public class TimeAop {

    @Pointcut("@annotation(com.study.mvc.aop.annotation.TimeAspect)")
    private void pointCut() {}

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        // 실행전 시간
        Object result = proceedingJoinPoint.proceed(); // 실제 실행될 메소드
        stopWatch.stop();
        // 실행 후 시간

        CodeSignature codeSignature = (CodeSignature) proceedingJoinPoint.getSignature();
        String className = codeSignature.getDeclaringTypeName();//클래스명 메서드명
        String methodName = codeSignature.getName();

        log.info("Timer: {}초 ({}.{})", stopWatch.getTotalTimeSeconds(), className, methodName);//해당메서드가 몇초 메서드명 클래스명 몇초걸렸는지?

        return result;
    }
}
