package com.study.mvc.repository;


import java.util.List;

public interface StudentRepository {

    public List<String> getStudentListAll();
    public String findStudentNameByIndex(int index);
    // 흐린이유 기본이라서
    // 추상클래스에서 public 기본
    // 다른 클래스 기본 default 같은패키지 안에서만
}
