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
    }//index
    
    //환율 정보 json data 가져오기
    @RequestMapping("/getInfo.do")
    public @ResponseBody String getInfo(){
        //JSON 데이터 전송 받을 uri    
        URI uri = UriComponentsBuilder.newInstance().scheme("http").host("www.apilayer.net")
                    .path("/api/live").queryParam("access_key","a397fa7d63626b279faabb900f9e3ed9").build().toUri();
        //"http://www.apilayer.net/api/live?access_key=a397fa7d63626b279faabb900f9e3ed9"; url
        RestTemplate restTpl = new RestTemplate();
        String responseStr = restTpl.getForObject(uri,String.class);
       return responseStr;
    }//getInfo

    //송금액을 통해 수취금액 DATA 돌려주기
    @RequestMapping("/getData.do")
    public @ResponseBody Map<String,Object> getData(@RequestParam HashMap<String,Object> param){
        //System.out.println(param);
        Map <String,Object> resultMap = new HashMap<String,Object>();
        //송금액 벨리데이션 체크 if
        if(converterService.chk((String)param.get("rmt"))){
            resultMap.put("err",false);
            resultMap.put("msg",converterService.getData(param));
        }else{
            resultMap.put("err",true);
        }//if else

        return resultMap;
    }//getData	
}//ConverterController
