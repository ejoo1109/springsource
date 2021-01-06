package com.company.factorial;

import org.springframework.stereotype.Component;

@Component("fact")
public class forCalc implements Calculator {
	
	@Override
	public long factorial(long num) {
		// for문으로 factorial 구하기
		long result = 1;
		for (int i = 1; i <= num; i++) {
			result *= i;
		}
		return result;
	}
}