package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.domain.Criteria;
import com.company.domain.ReplyPageVO;
import com.company.domain.ReplyVO;
import com.company.mapper.BoardMapper;
import com.company.mapper.ReplyMapper;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyMapper replymapper;
	@Autowired
	private BoardMapper boardmapper;
	
	@Transactional//만약 댓글 등록 실패시 로직을 수행하지 못하도록 트랜잭션 이용
	@Override
	public boolean regist(ReplyVO reply) {
		//게시글의 댓글 수 수정
		boardmapper.updateReplyCnt(reply.getBno(), 1);
		return replymapper.insert(reply)>0?true:false;
		
	}

	@Override
	public ReplyVO get(int rno) {
		return replymapper.read(rno);
	}

	@Override
	public ReplyPageVO getList(Criteria cri, int bno) {
		//replymapper.countBno(bno)-전체 댓글 갯수, replymapper.list(cri, bno)-댓글내용
		return new ReplyPageVO(replymapper.countBno(bno), replymapper.list(cri, bno));
	}

	@Override
	public boolean update(ReplyVO reply) {
		return replymapper.update(reply)>0?true:false;
	}

	@Transactional
	@Override
	public boolean delete(int rno) {
		//게시글의 댓글 수 수정
		ReplyVO reply=replymapper.read(rno);//rno 한개만 넘어오기 떄문에 bno를 한번 불러서 넘긴다.
		boardmapper.updateReplyCnt(reply.getBno(), -1);
		return replymapper.delete(rno)>0?true:false;
	}


}
