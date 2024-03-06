package com.study.mvc.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentRestController {

    @GetMapping("/ex/students")
    public ResponseEntity<?> students() {

        return ResponseEntity.ok("학생전체 조회");
    }

    @GetMapping("/ex/students/{index}")
    public ResponseEntity<?> student(@PathVariable int index) {

        return ResponseEntity.ok("학생 단건 조회:" + index);
    }



}
