package com.company.service;

import java.util.List;

import com.company.domain.BookVO;

public interface BookService {
	public boolean insertBook(BookVO vo);
	public boolean updateBook(BookVO vo);
	public boolean deleteBook(int code);
	public BookVO getRow(int code);
	public List<BookVO> getList();
	
}
