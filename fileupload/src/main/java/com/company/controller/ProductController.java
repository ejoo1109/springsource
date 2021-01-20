package com.company.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.domain.ProductVO;

import lombok.extern.slf4j.Slf4j;

//@Controller
//@Slf4j
//public class ProductController {
//	
//	@GetMapping("/product")
//	public void product() {
//		log.info("product 페이지 요청");
//	}
	//스트링 보내는 방식
//	@ResponseBody //return값 문자열 그대로 보내기
//	@PostMapping("/product") //jsp에서 데이터 가져오기
//	public String productPost( ProductVO vo) {
//		log.info("데이터 가져오기"+vo);	
//		return "success";
//	}
	
	//사용자가 넣어준 vo그대로 vo리턴
//	@ResponseBody //return값 문자열 그대로 보내기
//	@PostMapping(value="/product", produces=MediaType.APPLICATION_JSON_UTF8_VALUE) //jsp에서 데이터 가져오기
//	public ProductVO productPost( ProductVO vo) {
//		log.info("데이터 가져오기"+vo);	
//		return vo;
//	}
	
	//list 형태로 데이터 넘기기
//	@ResponseBody //return값 문자열 그대로 보내기
//	@PostMapping(value="/product", produces=MediaType.APPLICATION_JSON_UTF8_VALUE) //jsp에서 데이터 가져오기
//	public List<ProductVO> productPost(ProductVO vo) {
//		log.info("데이터 가져오기"+vo);	
//		List<ProductVO> list = new ArrayList<>();
//		
//		for(int i=0;i<3;i++) {
//			list.add(vo);
//		}
//		return list;
//	}
	
	//ResopneseEntity 사용하여 데이터 넘기기(String 방식)-@ResponseBody 사용안해도됨
	//return값 문자열 그대로 보내기+http상태코드
//	@PostMapping("/product") //jsp에서 데이터 가져오기
//	public ResponseEntity<String> productPost( ProductVO vo) {
//		log.info("데이터 가져오기"+vo);	
//		return vo!=null?
//				new ResponseEntity<String> ("success",HttpStatus.OK):
//				new ResponseEntity<String> ("fail",HttpStatus.BAD_REQUEST);
//	}
	
	//사용자가 넣어준 vo그대로 vo리턴
	//return값 문자열 그대로 보내기
//	@PostMapping(value="/product", produces=MediaType.APPLICATION_JSON_UTF8_VALUE) //jsp에서 데이터 가져오기
//     public ResponseEntity<ProductVO> productPost(ProductVO vo) {
//		log.info("데이터 가져오기"+vo);	
//		return new ResponseEntity<ProductVO>(vo,HttpStatus.OK);
//	}
	
	//list 형태로 데이터 넘기기
	//return값 문자열 그대로 보내기
//	@PostMapping(value="/product", produces=MediaType.APPLICATION_JSON_UTF8_VALUE) //jsp에서 데이터 가져오기
//     public ResponseEntity<List<ProductVO>> productPost(ProductVO vo) {
//		log.info("데이터 가져오기"+vo);	
//		List<ProductVO> list = new ArrayList<>();
//		
//		for(int i=0;i<3;i++) {
//			list.add(vo);
//		}
//		return new ResponseEntity<List<ProductVO>>(list,HttpStatus.OK);
//	} 
//	
	
	//jsp에서 json형태로 보낸 데이터 가져오기
//	@PostMapping("/product") 
//	public ResponseEntity<String> productPost(@RequestBody ProductVO vo) {
//		log.info("데이터 가져오기"+vo);	
//		return vo!=null?
//				new ResponseEntity<String> ("success",HttpStatus.OK):
//				new ResponseEntity<String> ("fail",HttpStatus.BAD_REQUEST);
//	}
	
//}
