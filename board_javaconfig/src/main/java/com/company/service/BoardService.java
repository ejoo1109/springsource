package com.company.service;

import java.util.List;

import com.company.domain.BoardVO;
import com.company.domain.Criteria;
import com.company.domain.FileAttach;

//DAO 작업 : 결과는 true or false(commit or rollback) 형태로 나오기 때문에 리턴타입이 boolean으로 작성
public interface BoardService {
	//게시글 등록
	public boolean regist(BoardVO board);
	//게시글 수정 + 첨부파일 삭제
	public boolean modify(BoardVO board);
	//게시글 삭제
	public boolean remove(int bno);
	//게시글 조회-개별
	public BoardVO getRow(int bno);
	//게시글 조회-전체
	public List<BoardVO> getList(Criteria cri);
	//전체 게시물 수
	public int getTotalCnt(Criteria cri);
	//첨부파일 조회
	public List<FileAttach> getAttachList(int bno);
	

}
