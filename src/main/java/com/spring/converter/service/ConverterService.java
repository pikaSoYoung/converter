package com.spring.converter.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

@Service
public class ConverterService {

    public double getData(HashMap<String,Object> param){

        double usd = Double.valueOf((String)param.get("info"));
        int rmt = Integer.parseInt((String)param.get("rmt"));
        
        return usd*rmt;
    }

    public boolean chk(String rmt){
        //빈문자열 체크
        if(rmt.equals("")){ return false; }
        //숫자외 다른 문자열 포함여부 체크
        return rmt.matches("^[0-9]*$");
    }
}    