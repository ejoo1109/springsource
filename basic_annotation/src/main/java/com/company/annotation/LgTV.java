package com.company.annotation;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("tv")
//객체 자동 생성 <bean id="tv" class="com.company.annotation.LgTV"/>와 같은 개념
public class LgTV implements TV {
	
	//적절한 시점에 생성된 객체 주입-@Autowired ,@Inject
	//이름 구별용 - @Qualifier 
	//객체 주입 + 이름구별 -
	
	
	//@Autowired //적절한 시점에 생성된 객체 주입
	@Inject
	@Qualifier("apple") //단독사용 불가(주입대상이 여러개인 경우 특정 객체 지정하는데 사용)
	
	private Speaker speaker;
	
//	private int price;
//	
//	public void setSpeaker(Speaker speaker) {
//		this.speaker = speaker;
//	}
//	public void setPrice(int price) {
//		this.price = price;
//	}
	
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