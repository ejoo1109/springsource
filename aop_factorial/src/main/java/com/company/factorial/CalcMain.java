package com.company.factorial;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CalcMain {

	public static void main(String[] args) {

		ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
		
		Calculator cal = (Calculator) ctx.getBean("fact");
		System.out.println("====================");
		System.out.println(" Loop 10!="+cal.factorial(10));
		System.out.println("====================");

		System.out.println();
		
		cal = (Calculator) ctx.getBean("rec");
		System.out.println("====================");
		System.out.println(" 재귀호출 10!="+cal.factorial(10));
		System.out.println("====================");
	}
}