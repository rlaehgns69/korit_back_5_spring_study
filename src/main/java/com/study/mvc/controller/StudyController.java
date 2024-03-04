package com.study.mvc.controller;

import com.study.mvc.model.HelloModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class StudyController {

    //MVC
    @GetMapping("/hello")
    public String helloPage(/*Model model*/Model model) {
        HelloModel helloModel = HelloModel.builder()
                .name1("김준일")
                .name2("김준이")
                .name3("김준삼")
                .build();
        model.addAttribute("h", helloModel);
//        model.addAttribute("name1", "김도훈");
//        model.addAttribute("name2", "김도일");
//        model.addAttribute("name3", "김도삼");
//        Map<String, Object> model = new HashMap<>();
//        model.put("name1","김준일");
//        model.put("name2","김준일");
//        model.put("name3","김준일");

        //return new ModelAndView("hello", model);//파일명
        return "hello";
    }
    @GetMapping("/test")
    @ResponseBody //viewName return x(age 반환 x) "test"리턴
//REST 데이터만 응답
    public Map<String, Object> testPage(/*Model model*/) {
//        model.addAttribute("age", 27);
        Map<String, Object> testObj = new HashMap<>();
        testObj.put("age",32);
        return testObj;
    }
}
