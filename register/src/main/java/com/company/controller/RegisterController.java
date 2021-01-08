package com.company.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.company.domain.AuthVO;
import com.company.domain.LoginVO;
import com.company.domain.RegisterVO;
import com.company.service.RegisterService;

import lombok.extern.slf4j.Slf4j;

//회원가입에 관련한 컨트롤러
@Controller
@Slf4j //Log4j2와 동일
@RequestMapping("/member/*")
public class RegisterController {
	
	@Autowired
	private RegisterService service;
	
	
	//index에서 회원가입을 누르면 스텝1페이지를 보여주는 메소드	
	@GetMapping("/step1") //http://localhost:8080/member/step1
	public void step1Get() {
		log.info("step1 페이지 요청"); //sysout 개념 
	}
	
	//step2 보여주는 컨트롤러 생성
	@PostMapping("/step2") //http://localhost:8080/member/step2
	public String step2Get(boolean agree, RedirectAttributes rttr) {
		log.info("step2 회원가입 페이지 요청"+ agree); 
		
		//사용자가 약관동의 체크한 값이 없다면 step1 되돌려보내기
		if(!agree) {
			rttr.addFlashAttribute("check","false");
			return "redirect:/member/step1"; 
			//redirect를 사용하지 않을경우 포워딩되기 떄문에 주소줄은 step2이지만 보여지는 페이지는 step1이다.
		}
		return "/member/step2";
	}
	
		@PostMapping("/step3") //step3페이지에서 들어올경우 실행되는 메소드
		public String step3Post(@ModelAttribute("regist")RegisterVO regist) {
			//step2.jsp에서 넘긴 값 가져오기
			//log 출력
			log.info("회원가입 요청 : "+regist);
			//두개의 비밀번호가 일치하지 않는다면 step2 돌려보내기
			//성공시 step3로 이동 (두개의 비밀번호가 일치한다면)
		if(regist.isPasswordEqualToConfirmPassword()) {
			service.register(regist);
			return "/member/step3";
		}else {
			return "/member/step2";
		}
	}
		
//http://localhost:8080/member/step3, http://localhost:8080/member/step2 바로 접속시 get방식이 아니라서 405에러
//get으로 바로 접속시에는 setp1로 돌아가게 요청 하는 핸들러
		@GetMapping(value= {"/step2","/step3"})
		public String handleStep2_3() {
			log.info("/step2, /step3 직접 요청");
			return "redirect:step1";
		}
		
	//중복아이디
		@ResponseBody  //리턴값이 .jsp가 아닌 실제 값을 의미하는 어노테이션- Ajax에서 값을 받음
		@PostMapping("/checkId")
		public String checkId(String userid) {
			log.info("중복 아이디 검사 요청..."+ userid);
			RegisterVO dupId=service.selectById(userid);
			if(dupId!=null) {
				return "false";
			}
			return "true";
		}
		
		//로그인 - siginin 보여주기
		@GetMapping("/signin")
		public void login() {
			log.info("login 페이지 요청");
		}
		
		//로그인 정보(아이디, 비밀번호)를 가져오는 컨트롤러
		@PostMapping("/signin")
		public void loginPost(LoginVO login) {
			log.info("로그인 페이지 요청..."+login);
			
			AuthVO auth=service.isLogin(login);
			log.info("auth"+auth);
			
		}
		
}
