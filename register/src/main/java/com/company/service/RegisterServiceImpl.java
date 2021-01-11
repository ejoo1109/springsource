package com.company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.AuthVO;
import com.company.domain.LoginVO;
import com.company.domain.RegisterVO;
import com.company.mapper.RegisterMapper;

@Service
public class RegisterServiceImpl implements RegisterService {
	
	@Autowired
	private RegisterMapper mapper;
	
	@Override
	public RegisterVO selectById(String userid) {
		return mapper.selectById(userid);
	}

	@Override
	public boolean register(RegisterVO regist) {
		return mapper.insert(regist)>0?true:false;
	}

	@Override
	public AuthVO isLogin(LoginVO login) {
		return mapper.selectByMember(login);
	}

	@Override
	public boolean leave(LoginVO login) {
		return mapper.leaveMember(login)>0?true:false;
	}

}
