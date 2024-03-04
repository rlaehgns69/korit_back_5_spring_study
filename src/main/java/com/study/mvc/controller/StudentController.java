package com.study.mvc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.mvc.dto.StudentReqDto;
import com.study.mvc.entity.Student;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController

public class StudentController {

    @PostMapping("/student")
    public ResponseEntity<?> addStudent(@CookieValue(required = false) String students, @RequestBody Student student) throws JsonProcessingException {
        //@RequestBody JSON
        List<Student> studentList = new ArrayList<>();
        int lastId = 0;

        System.out.println(students);

        if(students != null) {
            if(!students.isBlank()) {
                ObjectMapper studentsCookie = new ObjectMapper();
                studentList = studentsCookie.readValue(students, List.class);
                lastId = studentList.get(studentList.size() - 1).getStudentId();
            }
        }


        student.setStudentId(lastId + 1);
        studentList.add(student);

        ObjectMapper newStudentList = new ObjectMapper();
        String newStudents = newStudentList.writeValueAsString(studentList);

        System.out.println(students);
        ResponseCookie responseCookie = ResponseCookie
                .from("test", "test_data")
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(60)
                //.domain("localhost:8080")
                .build();
        // from 쿠키의 이름
        
        
        // (")문자 저장 x
        return ResponseEntity.created(null).header(HttpHeaders.SET_COOKIE, responseCookie.toString()).body(student);
    }

    @GetMapping("/student")
    // ?  dto 등 다른 형식들
    // useSearchParam
    public ResponseEntity<?> getStudentInfo(StudentReqDto studentReqDto) {
        System.out.println(studentReqDto);

        //return studentReqDto.toRespDto();
        return ResponseEntity.badRequest().header("test", "header_test").body(studentReqDto.toRespDto());
        // 상태코드 바디
    }
    // useParam
    @GetMapping("/student/{studentId}")
    public ResponseEntity<?> getStudent(@PathVariable int studentId) {//@PathVariable("studentId") int id
        // 매개변수 비우면 그냥 파라미터 됨.
        //? 뒤에 아니라 주소자체
        List<Student> studentList = List.of(
            new Student(1, "김도훈"),
            new Student(2, "김도이"),
            new Student(3, "김도삼"),
            new Student(4, "김도사")
        );

        // studentId가 5면 존재하지 않는 ID입니다. 출력
        Student findStudent = null;
        for(Student student : studentList) {
            if(student.getStudentId() == studentId) {
                findStudent = student;
            }
        }

        if(findStudent == null) {
            return ResponseEntity.badRequest().body(Map.of("errorMessage", "존재하지 않는 ID입니다."));
        }
        // new HashMap put put 안하고 자동으로 키밸류 키밸류 형식 Map.of List.of

        Optional<Student> optionalStudent
               = studentList.stream().filter(student -> student.getStudentId() == studentId).findFirst(); //stream안에 들어있는 거 바꿈.
        if(optionalStudent.isEmpty()) {
          return ResponseEntity.badRequest().body(Map.of("errorMessage", "존재하지 않는 ID입니다."));
        }
        findStudent = optionalStudent.get(); //orElseget
// list -> stream filter 화살표함수 첫번째객체 갖고온 id랑 일치하면 stream에 담아라. 하나만일치 findFirst 리턴자료형 Optional

        // collect(Collectors.toList()).get(0)


        return ResponseEntity.badRequest().body(findStudent);
    }
}
//    public StudentReqDto student( ) {
//        StudentReqDto studentReqDto =new StudentReqDto();
//        studentReqDto.setName("김도훈");
//        studentReqDto.setAge(27);
//        studentReqDto.setPhone("01012345766");
//        studentReqDto.setAddress("부산진구");
//
//        return studentReqDto;
//    }

//    @GetMapping("/student")
//    public Map<String, Object> getStudentInfo() {
//        Map<String, Object> studentObj = new HashMap<>();
//        studentObj.put("name","김도훈");
//        studentObj.put("age",27);
//        studentObj.put("phone","010-1234-5766");
//        studentObj.put("address","부산진구");
//        return studentObj;
//    }


