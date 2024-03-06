package com.study.mvc.repository;

import java.util.*;


public class StudentRepositoryImpl implements StudentRepository {
    public List<String> studentList = List.of("주환", "칭현", "홍렬");
    @Override
    public Map<String, String> getStudentListAll() {
        Map<String, String> studentMap = new HashMap<>();
        for(int i = 0; i < studentList.size()+1; i++) {

        }

    }

    @Override
    public String findStudentNameByIndex(int index) {

        System.out.println(index + "번 학생이름: "+ studentList.get(index));
        return "Hello";
    }
}
