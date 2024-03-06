package com.study.mvc.controller;

import com.study.mvc.diAndIoc.DiRepository;
import com.study.mvc.diAndIoc.DiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DiController {
    /**
     *
     * DI(Dependency Injection) - 의존성 주입
     */

    @GetMapping("/di") // 중복 불가
    // HTTP 요청 GET요청 /di 주소 client->HTTP 
    // 함수 호출 GetMapping /di로 호출을 할 때
    public ResponseEntity<?> diTest() {// 함수 정의 <>자료형이 변할 수 있다.
        // 리턴자료형 함수명(변하지 않음)
        DiRepository diRepository = new DiRepository();// 결합도 낮춤.1.객체 생성이 되면 변수와 메서드가 들어있는 주소 생성(자료형)
        // diRepository.변수, 기능 사용 가능 기능을 사용하려면 생성
        // DiRository의 값을 new DiService(new DiRepository());
        DiService diService = new DiService(diRepository);//Service 만들 때 매개변수 의존성주입
        // 200.100.getScoreList(바로 못가져옴.)
        // 주소 변수 = 주소자체(생성자 호출)
        // 생성 new (없는 주소(메모리주소)를)
        // new 함수 호출 매개변수 자료 값
        
        // 서비스가 레포지토리 의존
        // 컨트롤러가 DiService를 의존
        Map<String, Object> responseData = // 제네릭 <>자료형이 변할 수 있다. getTotal getAverage Object(int ,double ->double사용 가능(업캐스팅))
                Map.of(
                        "total", diService.getTotal(),//함수호출 메서드 호출(diService 함수정의)
                        "average", diService.getAverage() // Map.of 키밸류 키밸류 put 사용없이 데이터 추가
                        // 주소참조.
                ); // key - value
        return ResponseEntity.ok(responseData);// 응답 JSON
        // dto객체로 만들어서 json 응답

        //함수호출후 리턴 ok(responseData)정상 응답 client에 다시 반환 {"average":85, "total":84.5} 시작 끝 중간어떻게해서 responseData
        // ok만 body바로 넣을 수 있음.
        // ResponseEntity
    }
}
//@RestController public String diTest() {} return "test" 응답코드 응답헤더 응답데이터(body)
// return "test" HttpServletResponse response response.setStatus(400) / response.setContentType("application/json")
// response.setCharacterEncoding("UTF-8") /
// dispatcherServlet이 ResponseEntity<>