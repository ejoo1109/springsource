package com.company.mapper;

import java.util.List;

import com.company.domain.MemberVO;

public interface MemberMapper {
	public int insert(MemberVO member);
	public int update(MemberVO member);
	public int delete(MemberVO member);
	public MemberVO select(MemberVO member);
	public List<MemberVO> selectAll();
	
}
