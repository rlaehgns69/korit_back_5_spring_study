package com.study.mvc.diAndIoc;

import java.util.List;

public class DiService {

    private DiRepository diRepository;//일체형 결합도가 높다. private DiRepository = new DisRepository();
    //분리 diRepository공간(배터리)

    public DiService(DiRepository diRepository) {
        this.diRepository = diRepository;//함수(생성자) 정의
    }//결합도 낮춤.

    public int getTotal() {
//        DiRepository diRepository = new DiRepository(); 매번호출 할때마다 생성할 필요 x
//        결합도가 높다.(의존관계 o - 결합도가 높다.)
        int total = 0; //누적
        List<Integer> scoreList = diRepository.getScoreList();//전역 Repository

        for(Integer score: scoreList) {
            total += score;
        }
//        int result=0;
//        List<Integer> scoreList = List.of(100, 90, 80, 70) {
//            for(Integer score: scoreList) {
//                int total = 0;
//                total +=score;
//                result = total
//            }
//            return result;
//        }

        return total;
    }// getTotal동작을 위해서 Repository 무조건
    // 서비스가 동작을 하려면 객체가 필요(DiRepository)

    public double getAverage() {
//        DiRepository diRepository = new DiRepository();
        double avg = 0;
        int total = 0;
        List<Integer> scoreList = diRepository.getScoreList();
        for(Integer score: scoreList) {
            total += score;
        }
        avg = total / scoreList.size();

        return avg;
    }
}
