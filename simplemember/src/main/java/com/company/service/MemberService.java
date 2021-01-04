package com.company.service;

import java.util.List;

import com.company.domain.MemberVO;

public interface MemberService {
	public boolean memberInsert(MemberVO member);
	public boolean memberUpdate(MemberVO member);
	public boolean memberDelete(MemberVO member);
	public MemberVO getMember(MemberVO member);
	public List<MemberVO> getMemberList();
}
