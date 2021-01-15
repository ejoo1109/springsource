package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.Criteria;
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
	public List<ReplyVO> getList(Criteria cri, int bno) {
		return replymapper.list(cri, bno);
	}

	@Override
	public boolean update(ReplyVO reply) {
		return replymapper.update(reply)>0?true:false;
	}


}
