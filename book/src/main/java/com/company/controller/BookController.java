package com.company.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.company.domain.BookVO;
import com.company.service.BookService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BookController {
	@Autowired
	private BookService service;
	
	@PostMapping("/insert")
	public String insert(BookVO book, RedirectAttributes rttr) {
		log.info("도서 정보 입력 요청 "+book);
		
		try {//insert시 오류가 나면 exception 오류메세지를 보여주기 때문에 try-catch로 작업
			//delete는 오류가 나도 오류메세지를 따로 보여주지않기 때문에 try 사용안함.
			if(service.insertBook(book)) { //성공하면 리스트페이지
				return "redirect:/select";
			}else {
				return "redirect:/"; 
			}
			//실패하면 인덱스의 어떤 페이지를 보여줄것인지,
			//redirect 이용시 redirectAttributes객체 사용
		} catch (Exception e) {
			rttr.addFlashAttribute("tab","insert");
			return "redirect:/"; 
		}
	}
	
	//전체리스트
	@GetMapping("/select")
	public String selectAll(Model model) {
		log.info("전체 리스트 가져오기");
		
		//서비스 호출
		List<BookVO> list=service.getList();
		model.addAttribute("list",list);
		
		return "book_selectAll";
	}
	
	//도서정보 삭제
	@PostMapping("/delete")
	public String deletePost(int code, RedirectAttributes rttr) {
		log.info("도서 정보 삭제"+code);
		//성공하면 리스트 보여주기
			if(service.removeBook(code)) {
				return "redirect:/select";
			}else {
			//실패하면 index 의 도서정보 삭제페이지 보여주기
			rttr.addFlashAttribute("tab","delete");
			return "redirect:/"; 
	}
}
	//도서 정보 수정
	@PostMapping("/modify")
	public String updatePost(@Param("code")int code,@Param("price") int price, RedirectAttributes rttr) {
		log.info("도서 정보 수정"+code+" : "+price);
		//성공하면 리스트 보여주기
		if(service.updateBook(code, price)) {
			return "redirect:/select";
		}else {//실패하면 index보여주기
			rttr.addFlashAttribute("tab","modify");
			return "redirect:/"; 
		}
	}
	
	//도서 개별 조회
	@PostMapping("/search")
	public String search(String criteria,String keyword,Model model, RedirectAttributes rttr) {
		log.info("도서 정보 검색" +criteria+","+keyword);
		
		List<BookVO> list = service.searchList(criteria, keyword);
		//성공하면 book_searchAll
		if(!list.isEmpty()) { // = list.size() > 0
			model.addAttribute("list", list);	
			return "/book_searchAll";
		}else {		//실패하면 index
			rttr.addFlashAttribute("tab", "search");
			return "redirect:/";
		}
	}
	//도서 개별조회후 다시 도서정보검색을 눌렀을때
	@GetMapping("/search")
	public String searchGet(RedirectAttributes rttr) {
		log.info("search Form 요청");
		rttr.addFlashAttribute("tab", "search");
		return "redirect:/";
	}
}