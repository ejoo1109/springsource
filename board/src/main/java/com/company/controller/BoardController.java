package com.company.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.company.domain.BoardVO;
import com.company.domain.Criteria;
import com.company.domain.FileAttach;
import com.company.domain.PageVO;
import com.company.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/board/*")
public class BoardController {
	@Autowired
	private BoardService service;
	
	//게시글 작성 폼 보여주기
	@PreAuthorize("isAuthenticated()") //로그인 정보가 있는지 확인
	@GetMapping("/register") // /board/register.jsp페이지가 보여짐
	public void register() {
		log.info("게시글 작성 폼 보여주기");
	}
	
	//게시글 작성
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/register")
	public String registerPost(BoardVO board,RedirectAttributes rttr) {
		log.info("=====게시글 등록"+board);
		
		//파일 첨부 확인
//		if(board.getAttachList() != null) {
//			board.getAttachList().forEach(attach -> log.info(""+attach));
//		}
		
		log.info("게시글 번호 "+board.getBno());
		if(service.regist(board)) { //서비스임플에서 첨부파일이 있다면 리스트로 보내기
			//등록성공시 메시지를 모달로 띄우기 위해 조금 전 등록된 글 번호 보내기
			rttr.addFlashAttribute("result", board.getBno()); 
			return "redirect:list";			
		}
		return "/board/register"; //서비스임플에서 첨부파일이 없다면 글작성 페이지
	}
	//전체 리스트 보여주기
	@GetMapping("/list")
	public void list(Model model,Criteria cri) {
		log.info("=====게시글 보기");
		//전체 목록 요청
		List<BoardVO> list = service.getList(cri); 
		//전체 게시물 수 요청
		int total=service.getTotalCnt(cri);
		model.addAttribute("list",list);
		//pageVO라는 이름으로 PageVO에서 필요한 cri와 total값을 담음
		model.addAttribute("pageVO", new PageVO(cri, total)); 
	}
	
	//특정 게시물 보여주기 : 두가지 경로를 한개의 컨트롤러로 처리
	// /board/read?bno=5 -> read.jsp
	// /board/modify?bno=5 -> modify.jsp
	@GetMapping({"/read","/modify"}) 
	public void get(int bno,@ModelAttribute("cri")Criteria cri, Model model) {
		log.info("=====특정 게시글 보기"+bno);
		log.info("Criteria "+cri);
		BoardVO board = service.getRow(bno);
		model.addAttribute("board",board); // /board/read, /board/modify
	}
	
	//특정 게시물 삭제하기
	@PostMapping("/remove")
	@PreAuthorize("#writer == principal.username")
	public String remove(int bno,String writer, Criteria cri,RedirectAttributes rttr){
		log.info("=====삭제 요청"+bno);
		
		//게시물 번호에 해당하는 첨부파일 삭제(서버 폴더 파일 삭제,데이터베이스 삭제)
		
		//서버폴더 안 파일 삭제하기
		//1.bno에 해당하는 첨부파일 목록 알아내기
		List<FileAttach> attachList=service.getAttachList(bno);
		//성공하면 리스트 보여주기
		if(service.remove(bno)) { //2.데이터 베이스 삭제(게시글,첨부파일)
			
		//3.서버 폴더 파일 삭제
			deleteFiles(attachList); //파일삭제 메소드 호출
			rttr.addFlashAttribute("result","success"); //세션담기
		}
		
		//주소줄에 따라가는 형태 :객체로 못보내서 각각 보내야함
		rttr.addAttribute("type",cri.getType());
		rttr.addAttribute("keyword",cri.getKeyword());
		rttr.addAttribute("pageNum",cri.getPageNum());
		rttr.addAttribute("amount",cri.getAmount());
		
		return "redirect:list";
	
	}
	//서버파일 삭제
	
	private void deleteFiles(List<FileAttach> attachList) {
		log.info("=====첨부 파일 삭제" + attachList);
		
		if(attachList == null || attachList.size() <= 0) {
			return;
		}
		
		for(FileAttach attach: attachList) {
			Path path = Paths.get("c:\\upload\\",attach.getUploadPath()+"\\"+
					attach.getUuid()+"_"+attach.getFileName());
			
			//일반파일, 이미지 원본 파일 삭제
			try {
				Files.deleteIfExists(path); //원본 파일 삭제

				if(Files.probeContentType(path).startsWith("image")) {
					Path thumb = Paths.get("c:\\upload\\",attach.getUploadPath()+"\\s_"+
							attach.getUuid()+"_"+attach.getFileName());
					Files.delete(thumb); //썸네일 삭제
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	
	//특정 게시물 수정
	@PostMapping("/modify")
	@PreAuthorize("#board.writer == principal.username") //principal.username = id와 같음. 작성자와 로그인한 정보가 일치하는지 확인
	public String update(BoardVO board,Criteria cri, RedirectAttributes rttr) {
		log.info("=====수정요청"+board);
		log.info("criteria - "+cri);
		
		//수정된 파일 첨부 확인
		if(board.getAttachList() != null) {
			board.getAttachList().forEach(attach -> log.info(""+attach));
		}
		
		service.modify(board);
		
		rttr.addFlashAttribute("result","success");
		rttr.addAttribute("type",cri.getType());
		rttr.addAttribute("keyword",cri.getKeyword());
		rttr.addAttribute("pageNum",cri.getPageNum());
		rttr.addAttribute("amount",cri.getAmount());
		
		return "redirect:list";
	}
	//첨부파일 가져오기
	@GetMapping(value="/getAttachList",produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<FileAttach>> getAttachList(int bno){
		log.info("=====첨부파일 가져오기 : "+bno);
		return new ResponseEntity<List<FileAttach>>(service.getAttachList(bno),HttpStatus.OK);
	}
	
	
	
	
	
	}

