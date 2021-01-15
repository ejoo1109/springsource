package com.company.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegisterVO {
	private String userid;
	private String password;
	private String confirm_password;
	private String name;
	private String gender;
	private String email;
	
	//비밀번호 확인을 js에서 하지 않고 vo에 담아서 하는 방법
	public boolean isPasswordEqualToConfirmPassword() {
		return password.equals(confirm_password);
	}
	
}
