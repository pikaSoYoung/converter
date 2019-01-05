package com.spring.converter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import com.spring.converter.service.ConverterService;


@Controller
public class ConverterController {

    @Autowired
    private ConverterService converterService;
	
	@RequestMapping("/")
	public String index(){
		
		return "index";
    }
    
    @RequestMapping("/getInfo.do")
    public @ResponseBody String getInfo(){

        URI uri = UriComponentsBuilder.newInstance().scheme("http").host("www.apilayer.net")
                    .path("/api/live").queryParam("access_key","a397fa7d63626b279faabb900f9e3ed9").build().toUri();
        //"http://www.apilayer.net/api/live?access_key=a397fa7d63626b279faabb900f9e3ed9";
        RestTemplate restTpl = new RestTemplate();
        String responseStr = restTpl.getForObject(uri,String.class);
       return responseStr;
    }

    @RequestMapping("/getData.do")
    public @ResponseBody Map<String,Object> getData(@RequestParam HashMap<String,Object> param){
        System.out.println(param);
        Map <String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("msg",converterService.getData(param));
        return resultMap;
    }
	
}
