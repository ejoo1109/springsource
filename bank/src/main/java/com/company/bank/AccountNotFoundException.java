package com.company.bank;

public class AccountNotFoundException extends RuntimeException {
	public AccountNotFoundException() {
		System.out.println("계좌번호를 확인해 주세요");
	}
}
