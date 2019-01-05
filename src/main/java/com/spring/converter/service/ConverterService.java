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
}    