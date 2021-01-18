package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.Criteria;
import com.company.domain.ReplyPageVO;
import com.company.domain.ReplyVO;
import com.company.mapper.ReplyMapper;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyMapper replymapper;
	
	
	@Override
	public boolean regist(ReplyVO reply) {
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

	@Override
	public boolean delete(int rno) {
		return replymapper.delete(rno)>0?true:false;
	}


}
