package com.company.mapper;

import java.util.List;

import com.company.domain.BoardVO;

public interface BoardMapper {
	public int insert(BoardVO vo);
	public int update(BoardVO vo);
	public int delete(BoardVO vo);
	public BoardVO select(int bno);
	public List<BoardVO> selectAll();
	
	
}
