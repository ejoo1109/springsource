package com.company.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.company.domain.SampleVO;

import lombok.extern.slf4j.Slf4j;

@RestController //리턴하는 모든 값들은 실제 값이 됨(jsp 만들지 않음)
@Slf4j
public class RestControllerTest {
	
	@GetMapping(value="/hello",produces="text/plain;charset=utf-8") 
	//F12 network-> Response Headers->Content-Type: text/plain;charset=utf-8 지정한타입으로 반영됨
	public String sayHello() {
		log.info("hello 요청");
		return "Hello World";
	}
	
	@GetMapping(value="/sendVO", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	//produces : 어떤 형태로 보낼것인지 정함 , json형태로 전송
	public SampleVO sendVO() {
		SampleVO sample = new SampleVO();
		sample.setMno("12345");
		sample.setFirstName("hong");
		sample.setLastName("dong");
		return sample;
	}
	
	@GetMapping(value="/sendlist", produces= {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_XML_VALUE})
	//두개 지원하면 sendlist.json , sendlist.xml 둘다 가능
	public List<SampleVO> sendList() {
		
		List<SampleVO> list = new ArrayList<>();
		for(int i=0;i<10;i++) {
		SampleVO sample = new SampleVO();
		sample.setMno("12345");
		sample.setFirstName("hong");
		sample.setLastName("dong");
		list.add(sample);
	}
		return list;
}
	//http 상태코드+data 
	@GetMapping("/check") // ~~/check?height=180&weight=80
	public ResponseEntity<SampleVO> check(double height, double weight){
		SampleVO vo = new SampleVO();
		vo.setMno("4567");
		vo.setFirstName(height+"");
		vo.setLastName(weight+"");
		
		ResponseEntity<SampleVO> entity = ResponseEntity.status(HttpStatus.OK).body(vo);
		
		if(height < 150) {
			entity=ResponseEntity.status(HttpStatus.BAD_REQUEST).body(vo);
		}else {
			entity=ResponseEntity.status(HttpStatus.OK).body(vo);
		}
		return entity;
	}
	
	@GetMapping("/product/{cat}/{pid}") //localhost:8080/product/bags/1234
	public String[] getPath(@PathVariable("cat") String cat, @PathVariable("pid") String pid) {
		return new String [] {"category : "+cat, "productId : "+pid};
	}
}
