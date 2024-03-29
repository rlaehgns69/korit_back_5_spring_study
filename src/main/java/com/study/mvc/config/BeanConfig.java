package com.study.mvc.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Logger;

// Bean 수동 등록(2개 이상 등록 가능)

@Configuration
public class BeanConfig {

    @Bean
    public ObjectMapper ObjectMapper() {
        return new ObjectMapper(); // json으로 변할 때 마다 new
    }
    //컴포넌트 명 함수명 return 되는 값이 ioc에 등록
}
