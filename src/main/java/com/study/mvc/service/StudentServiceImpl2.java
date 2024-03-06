package com.study.mvc.service;

import com.study.mvc.dto.StudentExDto;
import com.study.mvc.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class StudentServiceImpl2 implements StudentService  {
    private final StudentRepository studentRepository;// private final ioc에서 RequiredArgsConstructor

    @Override
    //List return 자료형의 List ? map dto
    public List<?> getStudentList() {
        List<StudentExDto> studentDtoList = new ArrayList<>();
        List<String> studentList = studentRepository.getStudentListAll();

        for(String studentName: studentList) {
            studentDtoList.add(new StudentExDto(studentName));
        }

        return studentDtoList;
    }

    @Override
    public Object getStudent(int index) {
        String studentName = studentRepository.findStudentNameByIndex(index);
        return new StudentExDto(studentName);
    }
}