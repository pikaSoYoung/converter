package com.spring.converter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
	
	@RequestMapping("/")
	public @ResponseBody String index(){
		
		return "greetings form spring boot!";
	}
	
	@RequestMapping(value="/hello")
	public ModelAndView hello(){
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("hello");
		mv.addObject("name","hongGilDong");
		return mv;
	}
	
	@RequestMapping(value="/hello2")
	public ModelAndView hello2(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("hello");
		mv.addObject("name","hoho");
		return mv;
	}

}
