package com.company.poly;

public class SonySpeaker implements Speaker {
	public SonySpeaker() {
		System.out.println("=== SonySpeaker 객체 생성");
	}
	@Override
	public void volumUp() {
		System.out.println("=== SonySpeaker Volume Up");
	}
	@Override
	public void volumDown() {
		System.out.println("=== SonySpeaker Volume Down");
	}
}
