package com.study.mvc.controller;


import com.study.mvc.aop.annotation.ParamsAspect;
import com.study.mvc.dto.ParamsTestDto;
import com.study.mvc.service.AOPService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AOPController {

    @Autowired
    private AOPService aopService;

    @ParamsAspect //컨트롤러에서 찍을 필요가 없다. 이 어노테이션 덕분에
    @GetMapping("/aop/params") // controller 실행 get요청
    public ResponseEntity<?> paramsTest(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer option
    ) {

        //log.info("params: {}", paramsTestDto);

        // 데이터베이스에서 정보조회
        aopService.test("김도훈", 27); // 1.실행 시작점 메서드가 호출되려면

        return ResponseEntity.ok(null);
    }
}
