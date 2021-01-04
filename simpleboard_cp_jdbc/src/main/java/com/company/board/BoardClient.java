package com.company.board;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.company.domain.BoardVO;
import com.company.service.BoardService;

public class BoardClient {

	public static void main(String[] args) {
		//컨테이너 구동
		ApplicationContext ctx = new ClassPathXmlApplicationContext("board_config.xml");
		//서비스 호출
		BoardService service = (BoardService) ctx.getBean("service");
//		BoardVO vo = new BoardVO();
//		vo.setTitle("스프링 컨테이너");
//		vo.setContent("스프링 주요 개념 - DI");
//		vo.setWriter("홍길동");
//		
//		int result =service.insertBoard(vo);
//		
//		if(result>0) 
//			System.out.println("삽입 성공");
//	
		//전체 리스트
		List<BoardVO> list = service.getList();
		for(BoardVO vo1:list) {
			System.out.println(vo1.toString());
		}
//		//게시글 하나 가져오기
//		BoardVO row = service.getRow(10);
//			System.out.println(row.toString());
//		//게시글 하나 삭제하기
//		BoardVO deleteRow=new BoardVO();
//		deleteRow.setBno(12);
//		int delete = service.deleteBoard(deleteRow);
//		if(delete>0) {
//			System.out.println("삭제 성공");
//		}
		//게시글 수정하기
		BoardVO updateRow= new BoardVO();
		updateRow.setBno(11);
		updateRow.setTitle("스프링 주요 개념2");
		updateRow.setContent("스프링 주요 개념2 - DI");
		int update = service.updateBoard(updateRow);
		if(update>0) {
			System.out.println("수정 성공");
		}
	}

}
