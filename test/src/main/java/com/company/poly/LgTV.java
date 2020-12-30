package com.company.poly;

public class LgTV implements TV {
	
	private Speaker speaker;
	
	//방법1.객체생성하는것으로 생성자를 만듬
	public LgTV() {
		System.out.println("LgTV 객체 생성");
	}
	
	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}
	//방법2.매개변수로 받음
//	public LgTV(Speaker speaker) {
//		this.speaker = speaker; 
//	}
	
	@Override
	public void turnOn() {
		System.out.println("LGTV - 전원 On");
	}
	@Override
	public void turnOff() {
		System.out.println("LGTV - 전원 Off");
	}
	@Override
	public void soundUp() {
		//System.out.println("LGTV - 볼륨 Up");
		//SonySpeaker speaker = new SonySpeaker();
		speaker.volumUp();
	}
	@Override
	public void soundDown() {
		//System.out.println("LGTV - 볼륨 Down");
		//SonySpeaker speaker = new SonySpeaker();
		speaker.volumDown();
	}
	
}
