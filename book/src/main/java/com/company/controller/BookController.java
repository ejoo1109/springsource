package com.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.company.domain.BookVO;
import com.company.service.BookService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BookController {
	@Autowired
	private BookService service;
	
	@PostMapping("/insert")
	public String insert(BookVO book) {
		log.info("도서 정보 입력 요청 "+book);
		
		if(service.insertBook(book)) {
			return "redirect:/";
		}else {
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
}
