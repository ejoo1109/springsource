package com.company.member;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.company.domain.MemberVO;
import com.company.service.MemberService;



public class MemberClient {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("member_config.xml");
		MemberService service = (MemberService) ctx.getBean("service");
				// 전체조회 
				List<MemberVO> list = service.getMemberList();		
				for (MemberVO vo : list) {
					System.out.println(vo);
				}
//				
//				// 삽입 
//				MemberVO vo = new MemberVO();
//				vo.setUserid("hong234!");
//				vo.setPassword("hong1234!");
//				vo.setName("홍길길");
//				vo.setGender("여");
//				vo.setEmail("hong123@naver.com");
//				
//				if (service.memberInsert(vo)) {
//					System.out.println("삽입 성공");
//				} else {
//					System.out.println("삽입 실패");
//				}
				
//				// 수정 
//				MemberVO updatevo = new MemberVO();
//				updatevo.setUserid("hong234!");
//				updatevo.setPassword("hong3456!");
//			
//				if (service.memberUpdate(updatevo)) {
//					System.out.println("수정 성공");
//				} else {
//					System.out.println("수정 실패");
//				}
//				
//				// 삭제 
//				MemberVO deletevo = new MemberVO();
//				deletevo.setUserid("hong345!");
//				deletevo.setPassword("hong3456!");
//				
//				if (service.memberDelete(deletevo)) {
//					System.out.println("삭제 성공");
//				} else {
//				System.out.println("삭제 실패");
//				}
//
//				// 개별조회
//				MemberVO selectvo = new MemberVO();
//				selectvo.setUserid("hong234!");
//				selectvo.setPassword("hong3456!");
//				System.out.println("개별조회 : " + service.getMember(selectvo));
			}

	}

