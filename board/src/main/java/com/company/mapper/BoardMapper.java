package com.company.mapper;

import java.util.List;

import com.company.domain.BoardVO;
import com.company.domain.Criteria;
//BoardMapper.xml과 맞춰서 작성
//id는 메소드명 ()는 #{}과 일치하게 작성한다.
//결과값이 result>0 형식이기 때문에 리턴타입은 int 나 List가 된다.
public interface BoardMapper {
	public int insert(BoardVO board);
	public int update(BoardVO board);
	public int delete(int bno);
	public BoardVO read(int bno);
	public List<BoardVO> list(Criteria cri);
	public int totalCnt();
	
}
