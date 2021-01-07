package com.company.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.domain.LoginVO;

import lombok.extern.log4j.Log4j2;

@Log4j2 //로그처리할 어노테이션
@Controller
public class LoginController {
	
	//get->login,jsp,post-> 사용자가 입력한 데이터를 가져와서 db작업
	//@RequestMapping(value="/login",method=RequestMethod.GET) // http://localhost:8080/login
	@GetMapping("/login")
	public void loginGet() {
		log.info("login get..."); //login -> view 리졸버
	}
	
	//@RequestMapping(value="/login",method=RequestMethod.POST) // http://localhost:8080/login
//	@PostMapping("/login")
//	public void loginPost(HttpServletRequest request) { 
//  	// 방법1.기존 방법대로 사용자가 입력한 값 가져오기 request.getParameter
//		log.info("login post..."); //login -> view 리졸버
//		log.info("userid" +request.getParameter("userid"));
//		log.info("password" +request.getParameter("password"));
//	}
	
	@PostMapping("/login")
	public String loginPost(@RequestParam("userid")String userid, String password,Model model) { 
	    //방법2. 파라메터처리(단, name값 맞추기)
		log.info("login post..."); //login -> view 리졸버
		log.info("userid" +userid);
		log.info("password" +password);
		
		model.addAttribute("login", new LoginVO(userid, password));
		
		return "logout";
	}
	
//	@PostMapping("/login")
//	public String loginPost(LoginVO vo) { 
//		//방법3. vo에 담아서 사용자가 입력한 값 가져오기
//		log.info("login post..."); 
//		log.info("userid" +vo.getUserid());
//		log.info("password" +vo.getPassword());
//		return "logout"; //logout->view리졸버로 넘어감. 디폴트값: 포워드, logout페이지까지 vo를 담고 간다.
//	}
}
