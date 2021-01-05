package com.company.mybatis;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.company.damain.PersonVO;
import com.company.service.PersonService;

public class PersonClient {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
		PersonService service = (PersonService) ctx.getBean("person");
		//insert
		service.insertPerson("hong","홍길자");
		
		//update
		service.updatePerson("hong", "홍길길");
		
		//delete
		service.deletePerson("hong");
		
		//select-개별조회
		System.out.println(service.get("hong123"));
		
		//select-전체조회
		List<PersonVO> list = service.list();
		for(PersonVO vo:list) {
		System.out.println(vo);
		}
	}

}
