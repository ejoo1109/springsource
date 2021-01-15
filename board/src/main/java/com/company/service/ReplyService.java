package com.company.service;

import java.util.List;

import com.company.domain.Criteria;
import com.company.domain.ReplyVO;

public interface ReplyService {
	//댓글 작성
	public boolean regist(ReplyVO reply);
	//댓글 개별 조회
	public ReplyVO get(int rno);
	//댓글 전체리스트
	public List<ReplyVO> getList(Criteria cri, int bno);
	//댓글 수정
	public boolean update(ReplyVO reply);
	
}
