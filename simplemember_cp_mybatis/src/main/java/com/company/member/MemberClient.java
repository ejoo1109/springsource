package com.company.member;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.company.domain.MemberVO;
import com.company.service.MemberService;



public class MemberClient {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("member_config2.xml");
		MemberService service = (MemberService) ctx.getBean("service");
		
		List<MemberVO> list = service.getMemberList();
		for(MemberVO vo:list) {
			System.out.println(vo);
		}

	}

}
