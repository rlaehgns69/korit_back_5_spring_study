package com.study.mvc.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.mvc.repository.CarRepository;
import com.study.mvc.repository.CarRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private ObjectMapper objectMapper;

//    final String componentName = "a";
//
    @Autowired
    @Qualifier("a")
//    @Qualifier(componentName) // a콤포넌트를 가지고 와서 주입을 하겠다.
    private CarRepository carRepository;

    @Override

    public String getCarNames() {
//        StringBuilder stringBuilder = new StringBuilder();
//        List<String> nameList = carRepository.getCarNames();
//        for(String name: nameList) {
//            stringBuilder.append(name);
//        }
        return String.join(",", carRepository.getCarNames());
        // 문자열: k3, k5
    }

    @Override
    public int addCar(String carName) {
        return 0;
    }
}
