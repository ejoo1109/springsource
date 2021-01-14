package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.BookVO;
import com.company.mapper.BookMapper;

@Service("service")
public class BookServiceImpl implements BookService {

	@Autowired //dao에 repository 먼저 설정후 불러오는 개념
	private BookMapper mapper;
	
	@Override
	public boolean insertBook(BookVO vo) {
		
		return mapper.insert(vo)>0?true:false;
	}

	@Override
	public boolean updateBook(BookVO vo) {
		
		return mapper.update(vo)>0?true:false;
	}

	@Override
	public boolean deleteBook(int code) {
		
		return mapper.delete(code)>0?true:false;
	}

	@Override
	public BookVO getRow(int code) {
		
		return mapper.select(code);
	}

	@Override
	public List<BookVO> getList() {
		return mapper.selectAll();
	}

}
