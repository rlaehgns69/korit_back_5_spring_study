package com.study.mvc.diAndIoc;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component //ioc등록하겠다.
// final을 달고 RequiredArgsConstructor
@RequiredArgsConstructor
public class IoCService {
// NotBlank final scan을 할 때 Autowired도 보지만 RequiredArgsConstructor자동주입
    private final IoCRepository ioCRepository;
    //무조건 주입 r
    // equired=false

    public String getJson() throws JsonProcessingException {
        Map<String, String> nameMap = ioCRepository.convertNameMap();//null이어서 생성해줘야 함
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(nameMap);//Map을 json objectMapper(Map)을 Json으로 변경
    }
}
