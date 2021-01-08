package com.company.mapper;

import com.company.domain.AuthVO;
import com.company.domain.LoginVO;
import com.company.domain.RegisterVO;

public interface RegisterMapper {
	//mapper.xml에 작성했던 id값이랑 변수명이랑 동일하게 작성
	public RegisterVO selectById(String userid);
	public int insert(RegisterVO regist);
	public AuthVO selectByMember(LoginVO login);
}
