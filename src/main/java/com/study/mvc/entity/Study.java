package com.study.mvc.entity;

import com.study.mvc.dto.DBStudySelectRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Study {
    private int id;
    private String name;
    private int age;
    private LocalDateTime createDate;//나머지는 상관없음. 자바니까 camel

    public DBStudySelectRespDto toDto() {//메서드명
        return DBStudySelectRespDto.builder() //객체 리턴
                .id(id)
                .name(name)
                .age(age)//3개만 createDate없음
                .build();
        //얘가 가지고 있는 id name age build
    }
}
