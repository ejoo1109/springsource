package com.company.mapper;

import org.apache.ibatis.annotations.Insert;

public interface SampleMaper1 {

	@Insert("insert into tbl_sample1(col1) values(#{value})")
	public int inserCol(String value);
	
}