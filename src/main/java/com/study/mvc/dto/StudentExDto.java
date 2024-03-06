package com.study.mvc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
// component달면 무조건 하나가 스캔되서 ioc
// 매번 카피하면서 정보를 담는 객체
// autowired 안됨.
// 필요한거 넘겨줘야 됨.(매개변수)
public class StudentExDto {
    private String name;
}
