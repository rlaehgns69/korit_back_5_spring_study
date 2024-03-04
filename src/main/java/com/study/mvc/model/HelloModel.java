package com.study.mvc.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter // private getter
public class HelloModel {
    private String name1;
    private String name2;
    private String name3;

}
