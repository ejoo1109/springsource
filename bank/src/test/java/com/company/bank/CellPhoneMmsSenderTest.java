package com.company.bank;

import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


public class CellPhoneMmsSenderTest {
	final String MESSAGE ="테스트 문자 메세지";
	
	@Test
	public void testSend() {
		CellPhoneService mock= mock(CellPhoneService.class);
		CellPhoneMmsSender sender = new CellPhoneMmsSender(mock);
		
		sender.send(MESSAGE);
		verify(mock).sendMMS(MESSAGE);
	}
}
