package com.company.service;

import java.util.List;

import com.company.damain.PersonVO;

public interface PersonService {
	public int insertPerson(String id,String name);
	public int updatePerson(String id,String name);
	public int deletePerson(String id);
	public String get(String id);
	public List<PersonVO> list();
}
