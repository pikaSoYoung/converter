package com.spring.converter;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URL;
import java.util.HashMap;

import com.fasterxml.jackson.databind.util.JSONPObject;

@Controller
public class ConverterController {
	
	@RequestMapping("/")
	public String index(){
		
		return "index";
    }
    
    @RequestMapping("/getInfo.do")
    public @ResponseBody String getInfo(){

        URI uri = UriComponentsBuilder.newInstance().scheme("http").host("www.apilayer.net")
                    .path("/api/live").queryParam("access_key","a397fa7d63626b279faabb900f9e3ed9").build().toUri();

        //String url = "http://www.apilayer.net/api/live?access_key=a397fa7d63626b279faabb900f9e3ed9";
        RestTemplate restTpl = new RestTemplate();

        String responseStr = restTpl.getForObject(uri,String.class);

       return responseStr;
    }
	
}
