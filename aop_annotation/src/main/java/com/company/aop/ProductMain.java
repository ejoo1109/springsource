package com.company.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class ProductMain {

	public static void main(String[] args) /*throws Exception*/ {
		log.info("======= Product Main 실행 =======");
		
	ApplicationContext ctx = new ClassPathXmlApplicationContext("aop_config2.xml");

	Product product = (Product) ctx.getBean("product");
	product.setCompany("Bandai");
	product.setName("Bandai GUNDAM");
	product.setPrice("200000");
	product.getInfo();
	}
}