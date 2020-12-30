package com.company.poly;

public class LgTV implements TV {
	
	private Speaker speaker;
	
	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}
//	public LgTV() {
//		speaker = new SonySpeaker(); 
//	}

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
