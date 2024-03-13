package com.study.mvc.exception;

import lombok.Getter;

import java.util.Map;

//우리가 직접 예외생성
public class DuplicatedException extends RuntimeException {

    @Getter
    private Map<String, String> errorMap;

    // ctrl + o 생성자 오버라이딩
    public DuplicatedException(String message) {
        super(message);
        errorMap = Map.of("message", message);
    }
}
