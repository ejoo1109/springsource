package com.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.domain.NumVO;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/add/*") //http://localhost:8080/add/
public class AddController {
	
	@GetMapping("/sum")
	public void addForm() { //http://localhost:8080/add/sum
		log.info("add Form 요청..."); //add/sum -> view 리졸버
	}
	
//	@PostMapping("/sum")
//	public void addPost(@RequestParam("num1")int num,int num2) { //http://localhost:8080/add/sum
//		//@RequestParam("num1") 으로 이름이 다른걸 맞춰줄수 있다.
//		log.info("add Post 요청..."); //add/sum -> view 리졸버
//		log.info("num1 : "+num);
//		log.info("num2 : "+num2);
//	}
	
//	@PostMapping("/sum")//http://localhost:8080/add/sum
//	public String addPost(NumVO vo, Model model) { 
//		//@RequestParam("num1") 으로 이름이 다른걸 맞춰줄수 있다.
//		log.info("add Post 요청..."); //add/sum -> view 리졸버
//		log.info("num1 : "+vo.getNum1());
//		log.info("num2 : "+vo.getNum2());
//		
//		//request.setAttribute 랑 같은 역할
//		model.addAttribute("result",vo.getNum1()+vo.getNum2());
//		
//		return "add/result"; // 기본result -> view 리졸버 view/result.jsp경로
//	}
	@PostMapping("/sum")//http://localhost:8080/add/sum
	public String addPost(@ModelAttribute("vo") NumVO vo, Model model) { 
		//@RequestParam("num1") 으로 이름이 다른걸 맞춰줄수 있다.
		log.info("add Post 요청..."); //add/sum -> view 리졸버
		log.info("num1 : "+vo.getNum1());
		log.info("num2 : "+vo.getNum2());
		
		//request.setAttribute 랑 같은 역할
		model.addAttribute("result",vo.getNum1()+vo.getNum2());
		
		return "add/result"; // 기본result -> view 리졸버 view/result.jsp경로
}
}