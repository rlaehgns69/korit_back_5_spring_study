package com.study.mvc.dto;

import com.study.mvc.entity.Study;
import lombok.Data;

@Data
public class DBStudyReqDto {
    private String name;
    private int age;
    //toEntity
    public Study toEntity(int id) {
        return Study.builder()
                .id(id)//없으니까 밖에서 받아옴.
                .name(name)
                .age(age)
                .build();
    }
}
