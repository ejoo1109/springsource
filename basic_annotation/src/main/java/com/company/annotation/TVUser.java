package com.company.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TVUser {

	public static void main(String[] args) {
		//컨테이너 구동 : 쓸때마다 객체가 생성되는게 아니라 1개만 생성해서 씀=>싱글톤
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config2.xml");
		TV lg = (TV) ctx.getBean("tv");
		lg.turnOn();
		lg.soundUp();
		lg.soundDown();
		lg.turnOff();

	

	}

}