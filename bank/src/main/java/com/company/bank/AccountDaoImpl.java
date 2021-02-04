package com.company.bank;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AccountDaoImpl implements AccountDao {

	private Map<String,Account> accounts;
	
	public AccountDaoImpl() {
		accounts = Collections.synchronizedMap(new HashMap<String,Account>());
	}
	
	public boolean accountExistst(String accountNo) {
		return accounts.containsKey(accountNo);
	}
	
	@Override
	public void createAccount(Account account) {
		if(accountExistst(account.getAccountNo())) {
			throw new DuplicateAccountException();
		}
		accounts.put(account.getAccountNo(), account);
	}

	@Override
	public void updateAccount(Account account) {
		if(!accountExistst(account.getAccountNo())) {
			throw new AccountNotFoundException();
		}
		accounts.put(account.getAccountNo(), account);
	}

	@Override
	public void removeAccount(Account account) {
		if(!accountExistst(account.getAccountNo())) {
			throw new AccountNotFoundException();
		}
		accounts.remove(account.getAccountNo());
	}
		

	@Override
	public Account findAccount(String accountNo) {
		Account account = accounts.get(accountNo);
		if(account == null) {
			throw new AccountNotFoundException();
		}
		return account;
	}

}
