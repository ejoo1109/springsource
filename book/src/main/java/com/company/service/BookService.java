package com.company.service;

import java.util.List;

import com.company.domain.BookVO;

public interface BookService {
	//도서정보 입력
	public boolean insertBook(BookVO vo);
	//도서 정보 삭제
	public boolean removeBook(int code);
	//도서 정보 수정
	public boolean updateBook(int code, int price);
	//도서정보 전체조회
	public List<BookVO> getList();
	//도서정보 개별조회
	public List<BookVO> searchList(String criteria, String keyword);
	
}
