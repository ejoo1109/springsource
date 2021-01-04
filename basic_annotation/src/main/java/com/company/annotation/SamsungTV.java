package com.company.annotation;

public class SamsungTV implements TV {

	@Override
	public void turnOn() {
		System.out.println("SamsungTV - 전원 On");

	}

	@Override
	public void turnOff() {
		System.out.println("SamsungTV - 전원 Off");

	}

	@Override
	public void soundUp() {
		System.out.println("SamsungTV - 볼륨 Up");

	}

	@Override
	public void soundDown() {
		System.out.println("SamsungTV - 볼륨 Down");

	}

}