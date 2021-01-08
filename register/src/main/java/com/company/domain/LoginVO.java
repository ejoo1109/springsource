package com.company.domain;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Service
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class LoginVO {
	private String userid;
	private String password;
}
