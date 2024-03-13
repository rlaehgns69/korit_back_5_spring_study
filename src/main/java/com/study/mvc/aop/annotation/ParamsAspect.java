package com.study.mvc.aop.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
// 타입, 메서드, 필드 지정해서 사용 함수 위에 클래스위에 변수위에
//@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
// 어노테이션 적용시점 컴파일 되는 시점/ 컴파일 이후
// 일반 적으로 커스텀 어노테이션 런타임
public @interface ParamsAspect { }
