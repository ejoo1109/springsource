package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.MemberVO;
import com.company.persistence.MemberDAO;

@Service("service")
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDAO dao;
	
	
	@Override
	public boolean memberInsert(MemberVO member) {
		return dao.insert(member)>0?true:false;
	}

	@Override
	public boolean memberUpdate(MemberVO member) {
		return dao.update(member)>0?true:false;
	}

	@Override
	public boolean memberDelete(MemberVO member) {
		return dao.delete(member)>0?true:false;
	}

	@Override
	public MemberVO getMember(MemberVO member) {
		return dao.getRow(member);
	}

	@Override
	public List<MemberVO> getMemberList() {
		return dao.getList();
	}

}
