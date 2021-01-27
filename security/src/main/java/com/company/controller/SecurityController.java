package com.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/security/*")
public class SecurityController {

	@GetMapping("/all")
	public void allAccess() {
		log.info("모든 사람 가능");
	}
	@GetMapping("/member")
	public void memberAccess() {
		log.info("모든 멤버 가능");
	}
	@GetMapping("/admin")
	public void adminAccess() {
		log.info("관리자 가능");
	}
}
