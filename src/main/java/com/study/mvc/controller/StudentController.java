package com.study.mvc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.mvc.dto.StudentReqDto;
import com.study.mvc.entity.Student;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController

public class StudentController {

    @PostMapping("/student")
    public ResponseEntity<?> addStudent(@CookieValue(required = false) String students, @RequestBody Student student) throws JsonProcessingException, UnsupportedEncodingException {
        //@RequestBody JSON
        ObjectMapper objectMapper = new ObjectMapper();// ObjectMapper -> Gson
        List<Student> studentList = new ArrayList<>();
        // 1.비어있는 studentList생성
        int lastId = 0;//id자동증가


        System.out.println(students);

        if(students != null) {
            // 쿠키가 null이면
            if(!students.isBlank()) {
                // 문자열이 비어있으면 (키값은 있는데 비었을 수도)
                // for문 위 objectMapper 옮김
                for(Object object : objectMapper.readValue(students, List.class)) {
                    Map<String, Object> studentMap = (Map<String, Object>) object;
                    studentList.add(objectMapper.convertValue(studentMap, Student.class));
                }
                lastId = studentList.get(studentList.size() - 1).getStudentId();
            }
            //리스트로 변환하면 object로 (json자체가 리스트) jsonList를 변환해버리면 student객체 형태로 들어있으면 안에 Obejct형식
            // Object형식을 가져와서 각각을 Map(downcasting) 이 Map을 student객체로 변환해서 List로 변환해서 빼야된다.
        }


        student.setStudentId(lastId + 1);
        // 걸리지 않았으면 student 요청 때 보내준 JSON 데이터(@RequestBody Student student name은 있는데 id는 x)
        // 0+1
        studentList.add(student);
        // 비어있는 리스트 학생추가

        
        String studentListJson = objectMapper.writeValueAsString(studentList);
                                //객체를 JSON  tojson
        // 리스트 통째로 json
        System.out.println(studentListJson); //잘들어갔는지 출력
        
        ResponseCookie responseCookie = ResponseCookie
                //쿠키 객체 빌더
                // from( ,studentListJson) ""포함문자열로 들어감
                .from("students", URLEncoder.encode(studentListJson, "UTF-8")) // %5b 이런형식으로 들어감
                // 쿠키의 키값 변수명 String students 똑같이 (키값 변수명)
                .httpOnly(true)
                .secure(true)
                .path("/")
                // 모든영역 /
                // localhost:8080/auth 인증하는 부분에서만 사용하겠다. /auth 이경로 안에서만
                .maxAge(60) // 1이 분 60 한시간 쿠키 들어가고 나가고 60분 (현재시간 기준
                //.domain("localhost:8080")
                .build(); // 쿠키객체 생성
        // from 쿠키의 이름
        

        // (")문자 저장 x
        return ResponseEntity
                .created(null) // 201번 created 포스트요청 때 생성완료 했다. null을 했는데 추가작업을 하고 넘어감 보통 url
                .header(HttpHeaders.SET_COOKIE, responseCookie.toString()).
                body(student);
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


