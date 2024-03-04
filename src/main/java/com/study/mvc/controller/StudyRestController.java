package com.study.mvc.controller;

import com.study.mvc.dto.HelloDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller명: StudentController
 *
 * 메소드명: getStudentInfo()
 * 요청 메소드: Get
 * 요청 URL: /student
 * 요청 Params: name:, age, phone, address
 * 응답데이터: JSON(name, age, phone, address)
 */
;

@RestController //데이터 응답용 모든 메서드에 responseBody
//html x
// 페이지 자체 응답 x
public class StudyRestController {

    @GetMapping("/hello/test")
    //@ResponseBody
    public String hello(HelloDto helloDto) {
        // int age String parsing parseInt안해도 됨.

        // request HttpServletRequest

        // System.out.println(request.getMethod()); get요청 1
        // System.out.println(request.getParameter("name")); 2
        // System.out.println(n); @RequestParam("name") String name
        // System.out.println(name);// RequestParam String name, String name
        // System.out.println(age);
        System.out.println(helloDto);
        // getParameter 주소창 String servlet request.getParameter
        return "Hello";
    }
}
