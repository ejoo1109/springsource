package com.company.annotation;

import org.springframework.stereotype.Component;

@Component("apple")
public class AppleSpeaker implements Speaker {

	public AppleSpeaker() {
		System.out.println("=== AppleSpeaker 객체 생성");
	}
	
	@Override
	public void volumUp() {
		System.out.println("=== AppleSpeaker Volume Up");

	}

	@Override
	public void volumDown() {
		System.out.println("=== AppleSpeaker Volume Down");

	}
}