package com.company.bacnk;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

//테스트 클래스는 무조건 public 이어야함

public class CalculatorTest {

	@Test //해당 어노테이션을 통해서 이 메소드가 단위 테스트 메소드라는 것 명시
	public void testAdd() {
		Calculator cal= new Calculator();
		
		//첫번째: 기대값, 두번째: 리턴값, 세번째:허용 오차값
		assertEquals(50, cal.add(10, 40), 0);
	}
}
