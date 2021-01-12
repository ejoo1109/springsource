package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.BoardVO;
import com.company.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardMapper mapper;

	@Override
	public boolean regist(BoardVO board) {
		return mapper.insert(board)>0?true:false;
	}

	@Override
	public boolean modify(BoardVO board) {
		return mapper.update(board)>0?true:false;
	}

	@Override
	public boolean remove(int bno) {
		return mapper.delete(bno)>0?true:false;
	}

	@Override
	public BoardVO getRow(int bno) {
		return mapper.read(bno);
	}

	@Override
	public List<BoardVO> getList() {
		return mapper.list();
	}



}
