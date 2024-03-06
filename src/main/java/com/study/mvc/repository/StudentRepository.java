package com.study.mvc.repository;

import java.util.List;
import java.util.Map;

public interface StudentRepository {
    public Map<String, String> getStudentListAll();
    public String findStudentNameByIndex(int index);
}
