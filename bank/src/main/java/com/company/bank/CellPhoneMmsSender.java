package com.company.bank;

public class CellPhoneMmsSender {
	private CellPhoneService service;
	
	public CellPhoneMmsSender(CellPhoneService service) {
		this.service = service;
	}
	
	public void send(String msg) {
		service.sendMMS(msg);
	}
}
