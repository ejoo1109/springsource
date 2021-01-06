package com.company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.mapper.SampleMaper1;
import com.company.mapper.SampleMaper2;

@Service("sample")
public class SampleServiceImpl implements SampleService {

	@Autowired
	private SampleMaper1 mapper1;
	
	@Autowired
	private SampleMaper2 mapper2;
	
	@Transactional
	@Override
	public void addData(String value) {
		mapper1.inserCol(value); // 컬럼 500
		mapper2.inserCol(value); // 컬럼 50

	}
}