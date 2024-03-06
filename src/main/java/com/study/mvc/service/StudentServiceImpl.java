package com.study.mvc.service;

import com.study.mvc.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;


    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public String getStudentList() {
        return String.join(",", studentRepository.getStudentListAll());
    }

    @Override
    public int getStudent(int index) {
        return 0;
    }
}