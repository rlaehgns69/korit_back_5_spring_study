package com.study.mvc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.study.mvc.diAndIoc.DiRepository;
import com.study.mvc.diAndIoc.DiService;
import com.study.mvc.diAndIoc.IoCService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
//@RequiredArgsConstructor
public class IoCController {

//    private DiService diService;
// Component
//    public IoCController() {
//        DiRepository diRepository = new DiRepository();
//        diService = new DiService(diRepository);
//    }
    @Autowired
    private  IoCService ioCService;
    @GetMapping("/ioc")
    public ResponseEntity<?> iocTest() throws JsonProcessingException {
        String json = ioCService.getJson();//null이어서 생성해줘야 함
//        int total = diService.getTotal();
//        double avg = diService.getAverage();
//        Map<String, Object> map =
//                Map.of("total", total, "avg", avg); ok(map)

        return ResponseEntity.ok(json);
    }
}
