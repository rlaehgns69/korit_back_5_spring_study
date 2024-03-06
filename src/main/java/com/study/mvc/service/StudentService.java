package com.study.mvc.service;

import java.util.List;

public interface StudentService {

    public List<?> getStudentList(); // dto, Map
    public Object getStudent(int index);
}
