package com.study.mvc.service;

import com.study.mvc.dto.DBStudyReqDto;
import com.study.mvc.dto.DBStudyInsertRespDto;
import com.study.mvc.dto.DBStudySelectRespDto;
import com.study.mvc.entity.Study;
import com.study.mvc.repository.DBStudyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DBStudyService {
    @Autowired
    private DBStudyRepository dbStudyRepository;
    public DBStudyInsertRespDto createStudy(DBStudyReqDto dbStudyReqDto) {
//      [ AllArgs ]
        //Study study = new Study(0, dbStudyReqDto.getName(),dbStudyReqDto.getAge(),null);//id dbStudyReqDto.getName(),dbStudyReqDto.getAge(),null
//      [ NoArgs ]
//        Study study = new Study();
//        study.setName(dbStudyReqDto.getName());
//        study.setAge(dbStudyReqDto.getAge()); // dto를 entity로 만드는 과정
//      [ Builder ] builder쓰면 AllArgs자동 NoARgs빌더 사용 불가 builder안에 AllArgs
        // AllArgs는 무조건 vs Builder
        Study findStudy = dbStudyRepository.findStudyByName(dbStudyReqDto.getName());

        if(findStudy != null) {
            return DBStudyInsertRespDto.builder()
                    .successStatus(false)
                    .build();
        }
        Study study = Study.builder()
                        .name(dbStudyReqDto.getName())
                                .age(dbStudyReqDto.getAge())
                                        .build();

        int successCount = dbStudyRepository.save(study); //db에 insert AI 실행되기전에 getId없음
        // save 실행 xml id 결과 study keyProperty 다음 getId

        DBStudyInsertRespDto dbStudyInsertRespDto = DBStudyInsertRespDto.builder()
                .id(study.getId()) // id x ->servlet generatekeys 굳이 select문 x useGenerateKeys mybatis
                .name(study.getName()) // request, study
                .age(study.getAge()) // request, study
                .successStatus(successCount > 0)
                .successCount(successCount)
                .build();

        //return dbStudyRepository.save(study);//응답할 때 리턴
        return dbStudyInsertRespDto;
    }

    public DBStudySelectRespDto findStudyById(int id){
        Study study = dbStudyRepository.findStudyById(id);// study 레포지토리에서 가져옴.findStudyById를 이용해서

        System.out.println(study);

        DBStudySelectRespDto dbStudySelectRespDto =
                DBStudySelectRespDto.builder()
                        .id(study.getId())
                        .name(study.getName())
                        .age(study.getAge())
                        .build();
        return dbStudySelectRespDto;
    }

    public DBStudySelectRespDto findStudyByName(String name) {
        Study study = dbStudyRepository.findStudyByName(name);
        return study == null ? null: study.toDto();// -> Controller
    }
    // Dto도 리스트
    public List<DBStudySelectRespDto> findAll() {
        List<DBStudySelectRespDto> respDtoList = new ArrayList<>();//담을 공간 빈(DTO)
        List<Study> studyList =dbStudyRepository.findAll();
        // 하나꺼내서 바꿔서 넣기 Entity Dto로 바꿔서 담기
        for(Study study: studyList) {
            respDtoList.add(study.toDto());
        }
        return respDtoList;
    }

    public List<DBStudySelectRespDto> findAll2() {

        return dbStudyRepository.findAll()
                .stream()
                .map(Study::toDto)
                .collect(Collectors.toList());
    }//list를 stream으로 돌리고 toDto한걸 map해서 그 스트링을 toList

    public int deleteById(int id) {
        return dbStudyRepository.deleteById(id);
    }

    public int putById(int id, DBStudyReqDto dbStudyReqDto) {
    //toEntity
        Study study = dbStudyReqDto.toEntity(id);
        return dbStudyRepository.putById(study);
    }

    public int patchById(int id, DBStudyReqDto dbStudyReqDto) {
        return dbStudyRepository.patchById(dbStudyReqDto.toEntity(id));
    }


}
