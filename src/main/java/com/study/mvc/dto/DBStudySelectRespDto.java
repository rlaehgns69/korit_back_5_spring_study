package com.study.mvc.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class DBStudySelectRespDto {
    private int id;
    private String name;
    private int age;
    //private LocalDateTime localDateTime;
    // 이것만 Dto createDate xx

}
