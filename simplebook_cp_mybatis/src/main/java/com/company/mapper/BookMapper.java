package com.company.mapper;

import java.util.List;

import com.company.domain.BookVO;

public interface BookMapper {
	public int insert(BookVO vo);
	public int update(BookVO vo);
	public int delete(int code);
	public BookVO select(int code);
	public List<BookVO> selectAll();
	
}
