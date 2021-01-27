package com.company.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
//로그인정보, 탈퇴시 필요한 vo
public class LoginVO {
	private String userid;
	private String password;
}
