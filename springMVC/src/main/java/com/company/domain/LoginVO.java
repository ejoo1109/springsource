package com.company.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginVO {
	private String userid;
	private String password;
}
