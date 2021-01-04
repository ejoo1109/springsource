package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.BookVO;
import com.company.persistence.BookDAO;

@Service("service")
public class BookServiceImpl implements BookService {

	@Autowired //dao에 repository 먼저 설정후 불러오는 개념
	private BookDAO dao;
	
	@Override
	public boolean insertBook(BookVO vo) {
		
		return dao.insert(vo)>0?true:false;
	}

	@Override
	public boolean updateBook(BookVO vo) {
		
		return dao.update(vo)>0?true:false;
	}

	@Override
	public boolean deleteBook(int code) {
		
		return dao.delete(code)>0?true:false;
	}

	@Override
	public BookVO getRow(int code) {
		
		return dao.getRow(code);
	}

	@Override
	public List<BookVO> getList() {
		return dao.getList();
	}

}
