package com.company.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.domain.Criteria;
import com.company.domain.ReplyVO;

public interface ReplyMapper {
	//댓글 등록
	public int insert(ReplyVO reply);
	//댓글 개별 조회
	public ReplyVO read(int rno);
	//전체 댓글 조회
	public List<ReplyVO> list(@Param("cri") Criteria cri,@Param("bno") int bno);
	//전체 댓글 갯수 조회
	public int countBno(int bno);
	//댓글 수정
	public int update(ReplyVO reply);
	//댓글 삭제
	public int delete(int rno);
}
