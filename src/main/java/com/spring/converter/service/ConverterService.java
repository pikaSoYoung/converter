package com.spring.converter.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

@Service
public class ConverterService {

    public double getData(HashMap<String,Object> param){

        double usd = Double.valueOf((String)param.get("info"));
        int rmt = Integer.parseInt((String)param.get("rmt"));
        //계산된 결과값 리턴
        return usd*rmt;
    }

    public boolean chk(String rmt){
        //빈문자열 체크
        if(rmt.equals("")){ return false; }
        //숫자외 다른 문자열 포함여부 체크
        if(!rmt.matches("^[0-9]*$")) { return false; }
        //10000 USD 이상 체크
        if(Integer.parseInt(rmt)>10000) { return false; }
        return true;
    }
}    