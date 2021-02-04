package com.company.bank;

public class DuplicateAccountException extends RuntimeException {
	public DuplicateAccountException() {
		System.out.println("계좌번호가 중복되었습니다.");
	}
}
