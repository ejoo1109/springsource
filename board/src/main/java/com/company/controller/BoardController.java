package com.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.company.domain.BoardVO;
import com.company.domain.RegistVO;
import com.company.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/board/*")
public class BoardController {
	@Autowired
	private BoardService service;
	
	//게시글 작성 폼 보여주기
	@GetMapping("/register") // /board/register.jsp페이지가 보여짐
	public void register() {
		log.info("게시글 작성 폼 보여주기");
	}
	
	//게시글 작성
	@PostMapping("/register")
	public String registerPost(BoardVO board,RedirectAttributes rttr) {
		//if(service.regist()) {
		log.info("게시글 등록"+board);
		service.regist(board); 
		log.info("게시글 번호 "+board.getBno());
		//등록성공시 메시지를 모달로 띄우기 위해 조금 전 등록된 글 번호 보내기
		rttr.addFlashAttribute("result", board.getBno()); 
			return "redirect:list";
	}
	//전체 리스트 보여주기
	@GetMapping("/list")
	public void lis(Model model) {
		log.info("게시글 보기");
		
		List<BoardVO> list = service.getList();
		model.addAttribute("list",list);
	}
	
	//특정 게시물 보여주기 : 두가지 경로를 한개의 컨트롤러로 처리
	// /board/read?bno=5 -> read.jsp
	// /board/modify?bno=5 -> modify.jsp
	@GetMapping({"/read","/modify"}) 
	public void get(int bno,Model model) {
		log.info("특정 게시글 보기"+bno);
		
		BoardVO board = service.getRow(bno);
		model.addAttribute("board",board); // /board/read, /board/modify
	}
	
	//특정 게시물 삭제하기
	@PostMapping("/remove")
	public String remove(int bno){
		log.info("삭제요청"+bno);
		//성공하면 리스트 보여주기
		service.remove(bno);
		return "redirect:list";
	}
	
	//특정 게시물 수정
	@PostMapping("/modify")
	public String update(BoardVO board, RedirectAttributes rttr) {
		log.info("수정요청"+board);
		
		service.modify(board);
	//	rttr.addAttribute("board",board);
		return "redirect:list";
	}
	
	
	
	
	}

