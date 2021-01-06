package com.company.book;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.company.domain.BookVO;
import com.company.service.BookService;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class BookClient {
	public static void main(String[] args) {
		log.info("BookClient 실행");
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("book_config2.xml");
		BookService service = (BookService) ctx.getBean("service");
		
		
		//도서정보입력
		BookVO insertVO = new BookVO(1006,"자바스크립트","정인용",28000);
		if(service.insertBook(insertVO)) {
			System.out.println("도서 입력 성공");
	}

		//전체 리스트 가져오기
//		List<BookVO> list = service.getList();
//		for(BookVO vo:list) {
//			System.out.println(vo);
//		}
//		// 수정
//		BookVO vo = new BookVO();
//		vo.setCode(1007);
//		vo.setPrice(10000);
//		
//		if (service.updateBook(vo)) {
//			System.out.println("수정 성공");
//		} else {
//			System.out.println("수정 실패");
//		}
//				
//		// 삭제 
//		int deleteBook = 1006;
//		if (service.deleteBook(deleteBook)) {
//			System.out.println("삭제 성공");
//		} else {
//			System.out.println("삭제 실패");
//		}
//		
//		// 개별조회 
//		BookVO selectBook = service.getRow(1004);
//		System.out.println("개별조회 : " + selectBook.toString());
		
	}
}


