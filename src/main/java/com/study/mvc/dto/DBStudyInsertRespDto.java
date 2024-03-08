package com.study.mvc.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DBStudyInsertRespDto { //응답이니까 원하는 데이터만 가능
    private int id;
    private String name;
    private int age;
    // createdate(응답할 때 뺴고 주고 싶다.) Controller dbStudy.createStudy(dbStudyReqDto)
    private boolean successStatus;
    private int successCount;
}
