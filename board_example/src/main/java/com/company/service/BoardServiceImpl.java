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
	public boolean add(BoardVO board) {
		return mapper.insert(board)>0?true:false;
	}

	@Override
	public boolean delete(int idx) {
		return mapper.delete(idx)>0?true:false;
	}

	@Override
	public boolean modify(BoardVO board) {
		return mapper.update(board)>0?true:false;
	}

	@Override
	public BoardVO getRow(int idx) {
		return mapper.getList(idx);
	}

	@Override
	public List<BoardVO> allRow(BoardVO board) {
		return mapper.allList(board);
	}

}
