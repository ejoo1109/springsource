package com.company.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductVO {
	//jsp에서 form 데이터가 여러개일 경우 담을 vo생성
	private String code;
	private String category;
	private String pname;
	private int amount;
	private int price;
	private String etc;
	//private String file; 파일을 읽어오려고 변수를 설정해도 문자열로 읽어오기 때문에 에러발생!
}
