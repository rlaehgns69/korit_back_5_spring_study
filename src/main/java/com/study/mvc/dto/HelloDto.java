package com.study.mvc.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Builder //AllArgsConstructor 한개 (모든 값이 필수)
//@NoArgsConstructor //setter
@Data //getter setter toString
public class HelloDto {
    private String name;
    private int age;

}
