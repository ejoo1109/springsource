package com.company.mapper;

import org.apache.ibatis.annotations.Insert;

public interface SampleMaper2 {

	@Insert("insert into tbl_sample2(col1) values(#{value})")
	public int inserCol(String value);
	
}