package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.MemberVO;
import com.company.mapper.MemberMapper;

@Service("service")
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberMapper mapper;
	
	
	@Override
	public boolean memberInsert(MemberVO member) {
		return mapper.insert(member)>0?true:false;
	}

	@Override
	public boolean memberUpdate(MemberVO member) {
		return mapper.update(member)>0?true:false;
	}

	@Override
	public boolean memberDelete(MemberVO member) {
		return mapper.delete(member)>0?true:false;
	}

	@Override
	public MemberVO getMember(MemberVO member) {
		return mapper.select(member);
	}

	@Override
	public List<MemberVO> getMemberList() {
		return mapper.selectAll();
	}

}
