package com.company.service;

import java.util.List;

import com.company.domain.BoardVO;

public interface BoardService {
	public boolean add(BoardVO board);
	public boolean delete(int idx);
	public boolean modify(BoardVO board);
	public BoardVO getRow(int idx);
	public List<BoardVO> allRow(BoardVO board);
	
}
