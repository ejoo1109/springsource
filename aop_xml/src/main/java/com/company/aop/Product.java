package com.company.aop;

import org.springframework.stereotype.Component;

import lombok.Setter;

@Setter
@Component // 기본값 : 클래스명 기준으로 객체 생성(첫글자 소문자)
public class Product {
	
	private String company;
	private String name;
	private String price;
	
	public void getInfo() throws Exception {
		System.out.println("회사명 : "+company);
		System.out.println("제품명 : "+name);
		System.out.println("가격 : "+price);
		throw new Exception("강제 예외 발생");
	}
}