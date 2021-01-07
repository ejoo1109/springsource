package com.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller //@Component, @Service, @Repository 와 같은개념-객체생성
@RequestMapping("/sample/*") //http://localhost:8080/sample/~~~
public class SampleController {
	//@RequestMapping : 경로지정 actionFactory-cmd.equals("list.do"), 변경시 서버 재시작
	@RequestMapping("/basic") //http://localhost:8080/sample/basic
	public void basic() { //void일 경우 /sample/basic=>view 리졸버한테 넘어감
		log.info("basic...");
	}
	@RequestMapping("/test")
	public String test() {  //http://localhost:8080/sample/test
		log.info("test...");
		return "default"; // default -> view 리졸버한테 넘어감
	}
}
