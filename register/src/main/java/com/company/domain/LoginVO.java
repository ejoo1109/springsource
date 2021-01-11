package com.company.domain;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Service
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter

public class LoginVO {
	private String userid;
	private String password;
}
