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
		//하나의 테이블에서 오류가 난다면 두군데 다 실행을 못하게 하는작업
	}
}