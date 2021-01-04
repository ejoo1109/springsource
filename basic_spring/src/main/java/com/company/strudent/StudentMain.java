package com.company.strudent;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StudentMain {

	public static void main(String[] args) {
//		StudentInfo info = new StudentInfo(new Student("홍길동","15","1","4"));
//		info.getStudentInfo();
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config2.xml");
		StudentInfo info = (StudentInfo) ctx.getBean("info");
		info.getStudentInfo();
	}

}
