package com.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.domain.Criteria;
import com.company.domain.ReplyVO;
import com.company.service.ReplyService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/replies/*")
public class ReplyController {
	@Autowired
	private ReplyService service;
	
	// localhost:8080/replies/new + post
	@PostMapping("/new")
	public ResponseEntity<String> create(@RequestBody ReplyVO reply){
		log.info("댓글 삽입"+reply);
		
		return service.regist(reply)?
				new ResponseEntity<String>("success",HttpStatus.OK):
				new ResponseEntity<String>("fail",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// localhost:8080/replies/1 + get -JSON타입변환
	@GetMapping(value="/{rno}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ReplyVO> select(@PathVariable("rno") int rno){
		log.info("댓글 개별 조회"+rno);
		
		return 	new ResponseEntity<ReplyVO>(service.get(rno),HttpStatus.OK);
	}
	
	//전체 댓글 조회-localhost:8080/replies/pages/1030/2
	@GetMapping(value="/pages/{bno}/{page}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<ReplyVO>> getList(@PathVariable("bno") int bno,@PathVariable("page") int page){
		log.info("전체 댓글 조회"+bno);
		Criteria cri = new Criteria(page,10);
		return 	new ResponseEntity<List<ReplyVO>>(service.getList(cri, bno),HttpStatus.OK);
	}
	
	// 댓글 수정-localhost:8080/replies/2 + put + 수정할 내용
	//@PutMapping(value="/{rno}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@PutMapping("/{rno}")
	public ResponseEntity<String> update(@PathVariable("rno") int rno,@RequestBody ReplyVO reply){
		log.info("댓글 수정"+reply);
		
		reply.setRno(rno);
		return service.update(reply)?
				new ResponseEntity<String>("success",HttpStatus.OK):
				new ResponseEntity<String>("fail",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
