package com.company.mapper;

import java.util.List;

import com.company.domain.BoardVO;

public interface BoardMapper {
	public int insert(BoardVO board);
	public int delete(int idx);
	public int update(BoardVO board);
	public BoardVO getList(int idx);
	public List<BoardVO> allList(BoardVO board);
}
