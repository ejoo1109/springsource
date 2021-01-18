package com.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.company.domain.BoardVO;
import com.company.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/board/*")
public class BoardController {
	@Autowired
	private BoardService service;
	
	@GetMapping("/index")
	public void boardGet() {
		log.info("board 시작");
	}
	@GetMapping("/insert")
	public void insertdGet() {
		log.info("add 페이지");
	}
	@PostMapping("/insert")
	public String boardPost(BoardVO board,RedirectAttributes rttr) {
		log.info("add 작성완료"+board);
		service.add(board);
		return "redirect:list";
	}

	@GetMapping("/list")
	public String list(BoardVO board,Model model) {
		log.info("리스트 보기");
		List<BoardVO> list = service.allRow(board);
		model.addAttribute("list",list);
		
		return "/board/list";
	}
}
