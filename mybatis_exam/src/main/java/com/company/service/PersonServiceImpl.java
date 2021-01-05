package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.damain.PersonVO;
import com.company.mapper.PersonMapper;

@Service("person")
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonMapper mapper;
	
	@Override
	public int insertPerson(String id, String name) {
		return mapper.insert(id,name);
	}

	@Override
	public int updatePerson(String id, String name) {
		return mapper.update(id, name);
	}

	@Override
	public int deletePerson(String id) {
		return mapper.delete(id);
	}

	@Override
	public String get(String id) {
		return mapper.getPerson(id);
	}

	@Override
	public List<PersonVO> list() {
		return mapper.selectAll();
	}

}
