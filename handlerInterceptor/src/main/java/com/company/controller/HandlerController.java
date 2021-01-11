package com.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HandlerController {
	
	@GetMapping("/doA")
	public void doA() {
		log.info("doA 요청...");
	}
	
	@GetMapping("/doB") //예외 발생시 뒤에 나오는 postHandle 는 실행되지 않음
	public void doB() throws Exception {
		log.info("doB 요청...");
		throw new Exception("강제 발생");
	}
}
