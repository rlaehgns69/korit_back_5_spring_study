package com.study.mvc.controller;

import com.study.mvc.aop.annotation.TimeAspect;
import com.study.mvc.dto.DBStudyReqDto;
import com.study.mvc.service.DBStudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DBController {
    @Autowired
    private DBStudyService dbStudyService;

    
    // *(모든 리턴 타입) com.study.mvc.. *Controller .insert() .. 매개변수 상관없다. 이형식인 aop적용
    @PostMapping("/insert")
    public ResponseEntity<?> insert(@RequestBody DBStudyReqDto dbStudyReqDto) {

        //System.out.println(dbStudyReqDto); // name, age받아옴. DTO dbStudyReqDto

        return ResponseEntity.ok(dbStudyService.createStudy(dbStudyReqDto));
    }

    @GetMapping("/select/study/{id}")
    public ResponseEntity<?> selectStudy(@PathVariable int id) {

        return ResponseEntity.ok(dbStudyService.findStudyById(id));
    }

    @GetMapping("/select/study")//Get 요청 ?name=김준일
    public ResponseEntity<?> selectStudy(@RequestParam String name) {
        return ResponseEntity.ok(dbStudyService.findStudyByName(name));
    }
    //@TimeAspect
    @GetMapping("/select/studys")
    public ResponseEntity<?> selectStudyAll() {
        return ResponseEntity.ok(dbStudyService.findAll());
    }

    @DeleteMapping("/delete/study/{id}")
    public ResponseEntity<?> deleteStudy(@PathVariable int id) {
        return ResponseEntity.ok(dbStudyService.deleteById(id));
    }

    @PutMapping("/update/study/{id}")
    public ResponseEntity<?> updateStudy(
            @PathVariable int id, // 몇번째
            @RequestBody DBStudyReqDto dbStudyReqDto){//수정할 데이터 name age
    //Put 전체 수정{nickname: aaa, password: 1234} => {nickname:"", password:1111} db저장 put요청
        return ResponseEntity.ok(dbStudyService.putById(id, dbStudyReqDto));
    }
    @PatchMapping("/update/study/{id}")
    public ResponseEntity<?> patchStudy(
            @PathVariable int id, // 몇번째
            @RequestBody DBStudyReqDto dbStudyReqDto){//수정할 데이터 name age
    //Patch 부분 수정 {nickname: "aaa", password: 1111} //put patch 명시된 약속(안에 로직 내용을 바꿔야함.)
        return ResponseEntity.ok(dbStudyService.patchById(id, dbStudyReqDto));
    }
    // 전체 부분 수정 기준 정보를 수정할 때 myPage 자기소개칸 지우고 전송 지워진내용들어가야함.(Put)
    // 빈값을(빈, null) 보내면 수정한 내용이 없다.(Patch) 바뀐 부분만 업데이트


}
