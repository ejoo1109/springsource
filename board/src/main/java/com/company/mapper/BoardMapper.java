package com.company.mapper;

import java.util.List;

import com.company.domain.BoardVO;

public interface BoardMapper {
	public int insert(BoardVO board);
	public int update(BoardVO board);
	public int delete(int bno);
	public BoardVO select(int bno);
	public List<BoardVO> selectAll();
	
	
}
