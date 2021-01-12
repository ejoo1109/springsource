package com.company.service;

import java.util.List;

import com.company.domain.BoardVO;

//DAO 작업 : 결과는 result>0 형태로 나오기 때문에 리턴타입이 boolean으로 작성
public interface BoardService {
	//게시글 등록
	public boolean regist(BoardVO board);
	//게시글 수정
	public boolean modify(BoardVO board);
	//게시글 삭제
	public boolean remove(int bno);
	//게시글 조회-개별
	public BoardVO getRow(int bno);
	//게시글 조회-전체
	public List<BoardVO> getList();
}