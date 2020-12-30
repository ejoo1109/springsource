package com.company.app;

public class TVUser {

	public static void main(String[] args) {
		LgTV lg = new LgTV();
		
		lg.turnOn();
		lg.soundUp();
		lg.soundDown();
		lg.turnOff();

		SamsungTV samsung = new SamsungTV();
		samsung.powerOn();
		samsung.volumeUp();
		samsung.volumeDown();
		samsung.powerOff();
	}

}
